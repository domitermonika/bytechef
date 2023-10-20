
/*
 * Copyright 2021 <your company/name>.
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
 */

package com.bytechef.hermes.component.test;

import com.bytechef.atlas.execution.domain.Job;
import com.bytechef.atlas.execution.dto.JobParameters;
import com.bytechef.commons.util.MapUtils;
import com.bytechef.event.EventPublisher;
import com.bytechef.atlas.execution.service.ContextService;
import com.bytechef.atlas.execution.service.JobService;
import com.bytechef.atlas.execution.service.TaskExecutionService;
import com.bytechef.atlas.configuration.service.WorkflowService;
import com.bytechef.atlas.sync.executor.JobSyncExecutor;
import com.bytechef.atlas.worker.task.handler.TaskHandler;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class JobTestExecutor {

    private final ContextService contextService;
    private final JobService jobService;
    private final EventPublisher eventPublisher;
    private final TaskExecutionService taskExecutionService;
    private final Map<String, TaskHandler<?>> taskHandlerMap;
    private final WorkflowService workflowService;

    @SuppressFBWarnings("EI")
    public JobTestExecutor(
        ContextService contextService, JobService jobService, EventPublisher eventPublisher,
        TaskExecutionService taskExecutionService, Map<String, TaskHandler<?>> taskHandlerMap,
        WorkflowService workflowService) {

        this.contextService = contextService;
        this.jobService = jobService;
        this.eventPublisher = eventPublisher;
        this.taskExecutionService = taskExecutionService;
        this.taskHandlerMap = taskHandlerMap;
        this.workflowService = workflowService;
    }

    public Job execute(String workflowId, Map<String, Object> inputs) {
        return execute(workflowId, inputs, Map.of());
    }

    public Job execute(String workflowId, Map<String, Object> inputs, Map<String, TaskHandler<?>> taskHandlerMap) {
        JobSyncExecutor jobSyncExecutor = JobSyncExecutor.builder()
            .contextService(contextService)
            .eventPublisher(eventPublisher)
            .jobService(jobService)
            .taskExecutionService(taskExecutionService)
            .taskHandlerRegistry(MapUtils.concat(this.taskHandlerMap, taskHandlerMap)::get)
            .workflowService(workflowService)
            .build();

        return jobSyncExecutor.execute(new JobParameters(workflowId, inputs));
    }
}