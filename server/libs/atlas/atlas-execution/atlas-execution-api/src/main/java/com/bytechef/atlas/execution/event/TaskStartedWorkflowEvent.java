
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

package com.bytechef.atlas.execution.event;

import com.bytechef.event.AbstractWorkflowEvent;

/**
 * @author Ivica Cardic
 */
public class TaskStartedWorkflowEvent extends AbstractWorkflowEvent {

    public static final String TASK_STARTED = "task.started";

    private Long jobId;
    private long taskExecutionId;

    private TaskStartedWorkflowEvent() {
        super();
    }

    public TaskStartedWorkflowEvent(long taskExecutionId) {
        super(TASK_STARTED);

        this.taskExecutionId = taskExecutionId;
    }

    public TaskStartedWorkflowEvent(long jobId, long taskExecutionId) {
        super(TASK_STARTED);

        this.jobId = jobId;
        this.taskExecutionId = taskExecutionId;
    }

    public Long getJobId() {
        return jobId;
    }

    public long getTaskExecutionId() {
        return taskExecutionId;
    }

    @Override
    public String toString() {
        return "TaskStartedWorkflowEvent{" +
            "type='" + type + '\'' +
            ", jobId=" + jobId +
            ", taskExecutionId=" + taskExecutionId +
            ", createdDate=" + createdDate +
            "} ";
    }
}