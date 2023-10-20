
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

package com.bytechef.helios.execution.facade;

import com.bytechef.atlas.execution.dto.JobParameters;
import com.bytechef.atlas.execution.factory.JobFactory;
import com.bytechef.helios.configuration.domain.ProjectInstanceWorkflow;
import com.bytechef.helios.configuration.service.ProjectInstanceWorkflowService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

public class ProjectInstanceWorkflowJobFacadeImpl implements ProjectInstanceWorkflowJobFacade {

    private final JobFactory jobFactory;
    private final ProjectInstanceWorkflowService projectInstanceWorkflowService;

    @SuppressFBWarnings("EI")
    public ProjectInstanceWorkflowJobFacadeImpl(
        JobFactory jobFactory, ProjectInstanceWorkflowService projectInstanceWorkflowService) {

        this.jobFactory = jobFactory;
        this.projectInstanceWorkflowService = projectInstanceWorkflowService;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    @SuppressFBWarnings("NP")
    public long createJob(long instanceId, String workflowId) {
        ProjectInstanceWorkflow projectInstanceWorkflow = projectInstanceWorkflowService.getProjectInstanceWorkflow(
            instanceId, workflowId);

        long jobId = jobFactory.create(new JobParameters(workflowId, projectInstanceWorkflow.getInputs()));

        projectInstanceWorkflowService.addJob(Objects.requireNonNull(projectInstanceWorkflow.getId()), jobId);

        return jobId;
    }
}