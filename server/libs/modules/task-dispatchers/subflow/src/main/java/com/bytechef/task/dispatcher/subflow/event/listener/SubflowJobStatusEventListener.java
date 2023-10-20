
/*
 * Copyright 2016-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Modifications copyright (C) 2021 <your company/name>
 */

package com.bytechef.task.dispatcher.subflow.event.listener;

import com.bytechef.atlas.coordinator.event.ApplicationEvent;
import com.bytechef.atlas.coordinator.event.JobStopEvent;
import com.bytechef.atlas.coordinator.event.TaskExecutionCompleteEvent;
import com.bytechef.atlas.coordinator.event.TaskExecutionErrorEvent;
import com.bytechef.atlas.coordinator.event.listener.ApplicationEventListener;
import com.bytechef.atlas.file.storage.facade.WorkflowFileStorageFacade;
import com.bytechef.atlas.execution.domain.Job;
import com.bytechef.atlas.execution.domain.TaskExecution;
import com.bytechef.error.ExecutionError;
import com.bytechef.atlas.coordinator.event.JobStatusApplicationEvent;
import com.bytechef.atlas.execution.service.RemoteJobService;
import com.bytechef.atlas.execution.service.RemoteTaskExecutionService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * an {@link EventListener} which is used for listening to subflow job status events. When a sub-flow completes/fails or
 * stops its parent job and its parent task needs to be informed to resume its execution.
 *
 * @author Arik Cohen
 * @author Ivica Cardic
 * @since Sep 06, 2018
 * @see com.bytechef.task.dispatcher.subflow.SubflowTaskDispatcher
 */
public class SubflowJobStatusEventListener implements ApplicationEventListener {

    private final ApplicationEventPublisher eventPublisher;
    private final RemoteJobService jobService;
    private final RemoteTaskExecutionService taskExecutionService;
    private final WorkflowFileStorageFacade workflowFileStorageFacade;

    @SuppressFBWarnings("EI2")
    public SubflowJobStatusEventListener(
        ApplicationEventPublisher eventPublisher, RemoteJobService jobService,
        RemoteTaskExecutionService taskExecutionService, WorkflowFileStorageFacade workflowFileStorageFacade) {

        this.eventPublisher = eventPublisher;
        this.jobService = jobService;
        this.taskExecutionService = taskExecutionService;
        this.workflowFileStorageFacade = workflowFileStorageFacade;
    }

    @Override
    @SuppressFBWarnings("NP")
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof JobStatusApplicationEvent jobStatusApplicationEvent) {
            Job.Status status = jobStatusApplicationEvent.getStatus();
            Job job = jobService.getJob(jobStatusApplicationEvent.getJobId());

            if (job.getParentTaskExecutionId() == null) {
                return; // not a subflow -- nothing to do
            }

            switch (status) {
                case CREATED, STARTED -> {
                }
                case STOPPED -> {
                    TaskExecution subflowTaskExecution = taskExecutionService.getTaskExecution(
                        job.getParentTaskExecutionId());

                    eventPublisher.publishEvent(
                        new JobStopEvent(Objects.requireNonNull(subflowTaskExecution.getJobId())));

                }
                case FAILED -> {
                    TaskExecution erroredTaskExecution = taskExecutionService.getTaskExecution(
                        job.getParentTaskExecutionId());

                    erroredTaskExecution.setError(new ExecutionError("An error occurred with subflow", List.of()));

                    eventPublisher.publishEvent(new TaskExecutionErrorEvent(erroredTaskExecution));
                }
                case COMPLETED -> {
                    TaskExecution completionTaskExecution = taskExecutionService.getTaskExecution(
                        job.getParentTaskExecutionId());

                    Object output = job.getOutputs();

                    if (completionTaskExecution.getOutput() == null) {
                        completionTaskExecution.setOutput(
                            workflowFileStorageFacade.storeTaskExecutionOutput(
                                Objects.requireNonNull(completionTaskExecution.getId()), output));
                    } else {
                        // TODO check, it seems wrong
                        completionTaskExecution.evaluate(Map.of("execution", Map.of("output", output)));
                    }

                    eventPublisher.publishEvent(new TaskExecutionCompleteEvent(completionTaskExecution));
                }
                default -> throw new IllegalStateException("Unknown status: %s".formatted(status));
            }
        }
    }
}