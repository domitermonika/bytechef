
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

package com.bytechef.platform.config;

import com.bytechef.atlas.configuration.repository.WorkflowCrudRepository;
import com.bytechef.atlas.configuration.repository.WorkflowRepository;
import com.bytechef.atlas.configuration.service.WorkflowService;
import com.bytechef.atlas.execution.factory.JobFactory;
import com.bytechef.atlas.execution.factory.JobFactoryImpl;
import com.bytechef.atlas.execution.repository.ContextRepository;
import com.bytechef.atlas.execution.repository.CounterRepository;
import com.bytechef.atlas.execution.repository.JobRepository;
import com.bytechef.atlas.execution.repository.TaskExecutionRepository;
import com.bytechef.atlas.execution.service.*;
import com.bytechef.configuration.service.WorkflowServiceImpl;
import com.bytechef.event.EventPublisher;
import com.bytechef.message.broker.MessageBroker;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Ivica Cardic
 */
@Configuration
public class WorkflowConfiguration {

    @Bean
    ContextService contextService(ContextRepository contextRepository) {
        return new ContextServiceImpl(contextRepository);
    }

    @Bean
    CounterService counterService(CounterRepository counterRepository) {
        return new CounterServiceImpl(counterRepository);
    }

    @Bean
    JobFactory jobFactory(
        ContextService contextService, EventPublisher eventPublisher, JobService jobService,
        MessageBroker messageBroker, WorkflowService workflowService) {

        return new JobFactoryImpl(contextService, eventPublisher, jobService, messageBroker, workflowService);
    }

    @Bean
    JobService jobService(JobRepository jobRepository) {
        return new JobServiceImpl(jobRepository);
    }

    @Bean
    TaskExecutionService taskExecutionService(TaskExecutionRepository taskExecutionRepository) {
        return new TaskExecutionServiceImpl(taskExecutionRepository);
    }

    @Bean
    WorkflowService workflowService(
        CacheManager cacheManager, List<WorkflowCrudRepository> workflowCrudRepositories,
        List<WorkflowRepository> workflowRepositories) {

        return new WorkflowServiceImpl(cacheManager, workflowCrudRepositories, workflowRepositories);
    }
}