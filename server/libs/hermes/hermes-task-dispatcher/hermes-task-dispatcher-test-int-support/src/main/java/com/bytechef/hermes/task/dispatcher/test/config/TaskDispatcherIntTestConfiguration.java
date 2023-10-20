
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

package com.bytechef.hermes.task.dispatcher.test.config;

import com.bytechef.event.listener.EventListener;
import com.bytechef.event.EventPublisher;
import com.bytechef.atlas.configuration.repository.WorkflowRepository;
import com.bytechef.atlas.execution.repository.memory.InMemoryContextRepository;
import com.bytechef.atlas.execution.repository.memory.InMemoryCounterRepository;
import com.bytechef.atlas.execution.repository.memory.InMemoryJobRepository;
import com.bytechef.atlas.execution.repository.memory.InMemoryTaskExecutionRepository;
import com.bytechef.atlas.configuration.repository.resource.config.ResourceWorkflowRepositoryConfiguration;
import com.bytechef.atlas.execution.service.ContextService;
import com.bytechef.atlas.execution.service.ContextServiceImpl;
import com.bytechef.atlas.execution.service.CounterService;
import com.bytechef.atlas.execution.service.CounterServiceImpl;
import com.bytechef.atlas.execution.service.JobService;
import com.bytechef.atlas.execution.service.JobServiceImpl;
import com.bytechef.atlas.execution.service.TaskExecutionService;
import com.bytechef.atlas.execution.service.TaskExecutionServiceImpl;
import com.bytechef.atlas.configuration.service.WorkflowService;
import com.bytechef.configuration.service.WorkflowServiceImpl;
import com.bytechef.event.listener.EventListenerChain;
import com.bytechef.hermes.task.dispatcher.test.workflow.TaskDispatcherWorkflowTestSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.List;

/**
 * @author Ivica Cardic
 */
@ComponentScan("com.bytechef.hermes.task.dispatcher")
@EnableAutoConfiguration(
    exclude = {
        DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class
    })
@Import({
    ResourceWorkflowRepositoryConfiguration.class
})
@SpringBootConfiguration
public class TaskDispatcherIntTestConfiguration {

    @EnableCaching
    @TestConfiguration
    public static class CacheConfiguration {
    }

    @TestConfiguration
    public static class EventConfiguration {

        @Bean
        EventPublisher eventPublisher(List<EventListener> eventListeners) {
            EventListener eventListener = new EventListenerChain(eventListeners);

            return eventListener::onApplicationEvent;
        }
    }

    @TestConfiguration
    public static class WorkflowExecutionConfiguration {

        @Bean
        ContextService contextService() {
            return new ContextServiceImpl(new InMemoryContextRepository());
        }

        @Bean
        CounterService counterService() {
            return new CounterServiceImpl(new InMemoryCounterRepository());
        }

        @Bean
        JobService jobService(ObjectMapper objectMapper) {
            return new JobServiceImpl(new InMemoryJobRepository(taskExecutionRepository(), objectMapper));
        }

        @Bean
        TaskDispatcherWorkflowTestSupport taskDispatcherWorkflowTestSupport(
            ContextService contextService, CounterService counterService, EventPublisher eventPublisher,
            JobService jobService, TaskExecutionService taskExecutionService, WorkflowService workflowService) {

            return new TaskDispatcherWorkflowTestSupport(
                contextService, counterService, jobService, eventPublisher, taskExecutionService, workflowService);
        }

        @Bean
        TaskExecutionService taskExecutionService() {
            return new TaskExecutionServiceImpl(taskExecutionRepository());
        }

        @Bean
        InMemoryTaskExecutionRepository taskExecutionRepository() {
            return new InMemoryTaskExecutionRepository();
        }

        @Bean
        WorkflowService workflowService(List<WorkflowRepository> workflowRepositories) {
            return new WorkflowServiceImpl(new ConcurrentMapCacheManager(), Collections.emptyList(),
                workflowRepositories);
        }
    }
}
