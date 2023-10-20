
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

package com.bytechef.worker.config;

import com.bytechef.commons.webclient.DefaultWebClient;
import com.bytechef.event.EventPublisher;
import com.bytechef.hermes.component.ComponentDefinitionFactory;
import com.bytechef.hermes.component.context.factory.ContextConnectionFactory;
import com.bytechef.hermes.component.context.factory.ContextConnectionFactoryImpl;
import com.bytechef.hermes.component.context.factory.ContextFactoryImpl;
import com.bytechef.hermes.component.definition.Authorization;
import com.bytechef.hermes.component.definition.Authorization.ApplyResponse;
import com.bytechef.hermes.connection.service.ConnectionService;
import com.bytechef.hermes.data.storage.service.DataStorageService;
import com.bytechef.hermes.definition.registry.component.factory.ComponentHandlerListFactory;
import com.bytechef.hermes.definition.registry.component.ComponentDefinitionRegistry;
import com.bytechef.hermes.definition.registry.component.ComponentDefinitionRegistryImpl;
import com.bytechef.hermes.component.context.factory.ContextFactory;
import com.bytechef.hermes.definition.registry.domain.ConnectionDefinition;
import com.bytechef.hermes.definition.registry.domain.OAuth2AuthorizationParameters;
import com.bytechef.hermes.definition.registry.facade.ActionDefinitionFacade;
import com.bytechef.hermes.definition.registry.facade.ActionDefinitionFacadeImpl;
import com.bytechef.hermes.definition.registry.facade.ComponentDefinitionFacade;
import com.bytechef.hermes.definition.registry.facade.ComponentDefinitionFacadeImpl;
import com.bytechef.hermes.definition.registry.facade.TriggerDefinitionFacade;
import com.bytechef.hermes.definition.registry.facade.TriggerDefinitionFacadeImpl;
import com.bytechef.hermes.definition.registry.remote.client.service.ConnectionDefinitionServiceClient;
import com.bytechef.hermes.definition.registry.service.ActionDefinitionService;
import com.bytechef.hermes.definition.registry.service.ActionDefinitionServiceImpl;
import com.bytechef.hermes.definition.registry.service.ComponentDefinitionService;
import com.bytechef.hermes.definition.registry.service.ComponentDefinitionServiceImpl;
import com.bytechef.hermes.definition.registry.service.ConnectionDefinitionService;
import com.bytechef.hermes.definition.registry.service.ConnectionDefinitionServiceImpl;
import com.bytechef.hermes.definition.registry.service.TriggerDefinitionService;
import com.bytechef.hermes.definition.registry.service.TriggerDefinitionServiceImpl;
import com.bytechef.hermes.file.storage.service.FileStorageService;
import com.bytechef.message.broker.MessageBroker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
public class DefinitionRegistryConfiguration {

    @Bean
    ActionDefinitionFacade actionDefinitionFacade(
        ActionDefinitionService actionDefinitionService, ConnectionService connectionService) {

        return new ActionDefinitionFacadeImpl(actionDefinitionService, connectionService);
    }

    @Bean
    ActionDefinitionService actionDefinitionService(
        ComponentDefinitionRegistry componentDefinitionRegistry, ContextConnectionFactory contextConnectionFactory,
        ContextFactory contextFactory) {

        return new ActionDefinitionServiceImpl(componentDefinitionRegistry, contextConnectionFactory, contextFactory);
    }

    @Bean
    ComponentDefinitionFacade componentDefinitionFacade(
        ComponentDefinitionService componentDefinitionService, ConnectionService connectionService) {

        return new ComponentDefinitionFacadeImpl(componentDefinitionService, connectionService);
    }

    @Bean
    public ComponentDefinitionRegistry componentDefinitionRegistry(
        List<ComponentDefinitionFactory> componentDefinitionFactories,
        ComponentHandlerListFactory componentHandlerListFactory) {

        return new ComponentDefinitionRegistryImpl(
            componentDefinitionFactories, componentHandlerListFactory);
    }

    @Bean
    ComponentDefinitionService componentDefinitionService(ComponentDefinitionRegistry componentDefinitionRegistry) {
        return new ComponentDefinitionServiceImpl(componentDefinitionRegistry);
    }

    @Bean
    ConnectionDefinitionService connectionDefinitionService(
        ComponentDefinitionRegistry componentDefinitionRegistry,
        ConnectionDefinitionServiceClient connectionDefinitionServiceClient) {

        return new WorkerConnectionDefinitionService(
            new ConnectionDefinitionServiceImpl(componentDefinitionRegistry), connectionDefinitionServiceClient);
    }

    @Bean
    ConnectionDefinitionServiceClient connectionDefinitionServiceClient(
        DefaultWebClient defaultWebClient, DiscoveryClient discoveryClient, ObjectMapper objectMapper) {

        return new ConnectionDefinitionServiceClient(defaultWebClient, discoveryClient, objectMapper);
    }

    @Bean
    ContextConnectionFactory contextConnectionFactory(
        ComponentDefinitionService componentDefinitionService,
        ConnectionDefinitionService connectionDefinitionService) {

        return new ContextConnectionFactoryImpl(componentDefinitionService, connectionDefinitionService);
    }

    @Bean
    ContextFactory contextFactory(
        ConnectionDefinitionService connectionDefinitionService, ConnectionService connectionService,
        DataStorageService dataStorageService, EventPublisher eventPublisher, FileStorageService fileStorageService) {

        return new ContextFactoryImpl(
            connectionDefinitionService, connectionService, dataStorageService, eventPublisher, fileStorageService);
    }

    @Bean
    TriggerDefinitionFacade triggerDefinitionFacade(
        ConnectionService connectionService, TriggerDefinitionService triggerDefinitionService) {

        return new TriggerDefinitionFacadeImpl(connectionService, triggerDefinitionService);
    }

    @Bean
    TriggerDefinitionService triggerDefinitionService(
        ComponentDefinitionRegistry componentDefinitionRegistry, ContextConnectionFactory contextConnectionFactory,
        ContextFactory contextFactory, MessageBroker messageBroker) {

        return new TriggerDefinitionServiceImpl(
            componentDefinitionRegistry, contextConnectionFactory, contextFactory, messageBroker);
    }

    /**
     * Compound ConnectionDefinitionService impl that supports the use case where a component (for example, HttpClient
     * or Script) uses a compatible connection from a different component that can be in a different worker instance.
     */
    private static class WorkerConnectionDefinitionService implements ConnectionDefinitionService {

        private final ConnectionDefinitionService connectionDefinitionService;
        private final ConnectionDefinitionServiceClient connectionDefinitionServiceClient;

        public WorkerConnectionDefinitionService(
            ConnectionDefinitionService connectionDefinitionService,
            ConnectionDefinitionServiceClient connectionDefinitionServiceClient) {

            this.connectionDefinitionService = connectionDefinitionService;
            this.connectionDefinitionServiceClient = connectionDefinitionServiceClient;
        }

        @Override
        public boolean connectionExists(String componentName, int connectionVersion) {
            return connectionDefinitionService.connectionExists(componentName, connectionVersion);
        }

        /**
         * Called from the Context.Connection instance.
         */
        @Override
        public ApplyResponse executeAuthorizationApply(
            String componentName, int connectionVersion, Map<String, ?> connectionParameters,
            String authorizationName) {

            if (connectionDefinitionService.connectionExists(componentName, connectionVersion)) {
                return connectionDefinitionService.executeAuthorizationApply(
                    componentName, connectionVersion, connectionParameters, authorizationName);
            } else {
                return connectionDefinitionServiceClient.executeAuthorizationApply(
                    componentName, connectionVersion, connectionParameters, authorizationName);
            }
        }

        /**
         * Called from the ConnectionFacade instance.
         */
        @Override
        public Authorization.AuthorizationCallbackResponse executeAuthorizationCallback(
            String componentName, int connectionVersion, Map<String, ?> connectionParameters,
            String authorizationName, String redirectUri) {

            return connectionDefinitionService.executeAuthorizationCallback(
                componentName, connectionVersion, connectionParameters, authorizationName, redirectUri);
        }

        /**
         * Called from the Context.Connection instance.
         */
        @Override
        public Optional<String> executeBaseUri(
            String componentName, int connectionVersion, Map<String, ?> connectionParameters) {
            if (connectionDefinitionService.connectionExists(componentName, connectionVersion)) {
                return connectionDefinitionService.executeBaseUri(
                    componentName, connectionVersion, connectionParameters);
            } else {
                return connectionDefinitionServiceClient.executeBaseUri(
                    componentName, connectionVersion, connectionParameters);
            }
        }

        @Override
        public Authorization.AuthorizationType getAuthorizationType(
            String authorizationName, String componentName, int connectionVersion) {

            return connectionDefinitionService.getAuthorizationType(
                authorizationName, componentName, connectionVersion);
        }

        @Override
        public ConnectionDefinition getConnectionDefinition(String componentName, int componentVersion) {
            return connectionDefinitionService.getConnectionDefinition(componentName, componentVersion);
        }

        @Override
        public List<ConnectionDefinition> getConnectionDefinitions(String componentName, Integer componentVersion) {
            return connectionDefinitionService.getConnectionDefinitions(componentName, componentVersion);
        }

        @Override
        public List<ConnectionDefinition> getConnectionDefinitions() {
            return connectionDefinitionService.getConnectionDefinitions();
        }

        @Override
        public OAuth2AuthorizationParameters getOAuth2AuthorizationParameters(
            String componentName, int connectionVersion, Map<String, ?> connectionParameters,
            String authorizationName) {

            return connectionDefinitionService.getOAuth2AuthorizationParameters(
                componentName, connectionVersion, connectionParameters, authorizationName);
        }
    }
}