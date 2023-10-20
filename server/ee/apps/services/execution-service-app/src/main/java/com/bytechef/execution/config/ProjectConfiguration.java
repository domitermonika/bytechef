
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

package com.bytechef.execution.config;

import com.bytechef.atlas.configuration.service.WorkflowService;
import com.bytechef.atlas.execution.service.JobService;
import com.bytechef.helios.configuration.service.ProjectInstanceService;
import com.bytechef.helios.configuration.service.ProjectService;
import com.bytechef.helios.execution.facade.ProjectWorkflowExecutionFacade;
import com.bytechef.helios.execution.facade.ProjectWorkflowExecutionFacadeImpl;
import com.bytechef.hermes.execution.facade.JobFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ivica Cardic
 */
@Configuration
public class ProjectConfiguration {

    @Bean
    ProjectWorkflowExecutionFacade projectWorkflowExecutionFacade(
        JobFacade jobFacade, JobService jobService, ProjectInstanceService projectInstanceService,
        ProjectService projectService, WorkflowService workflowService) {

        return new ProjectWorkflowExecutionFacadeImpl(
            jobFacade, jobService, projectInstanceService, projectService, workflowService);
    }
}