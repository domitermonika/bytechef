
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

package com.bytechef.hermes.component.oas.loader;

import com.bytechef.commons.util.CollectionUtils;
import com.bytechef.commons.util.OptionalUtils;
import com.bytechef.hermes.component.ComponentHandler;
import com.bytechef.hermes.component.OpenApiComponentHandler;
import com.bytechef.hermes.component.definition.ActionDefinition;
import com.bytechef.hermes.component.definition.ActionDefinitionWrapper;
import com.bytechef.hermes.component.definition.ComponentDefinition;
import com.bytechef.hermes.component.definition.ComponentDefinitionWrapper;
import com.bytechef.hermes.component.definition.ComponentHandlerWrapper;
import com.bytechef.hermes.component.handler.DefaultComponentActionTaskHandler;
import com.bytechef.hermes.component.loader.ComponentDefinitionFactoryBeanDefinitionLoader;
import com.bytechef.hermes.component.util.OpenApiClientUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

/**
 * @author Ivica Cardic
 */
public class OpenAPIComponentDefinitionFactoryBeanDefinitionLoader
    implements ComponentDefinitionFactoryBeanDefinitionLoader {

    @Override
    public List<ComponentDefinitionFactoryBeanDefinition> loadComponentDefinitionFactoryBeanDefinitions() {
        List<ComponentDefinitionFactoryBeanDefinition> componentTaskHandlerFactories = new ArrayList<>();

        for (OpenApiComponentHandler openApiComponentHandler : ServiceLoader.load(OpenApiComponentHandler.class)) {
            ComponentDefinition componentDefinition = openApiComponentHandler.getDefinition();

            List<? extends ActionDefinition> actionDefinitions = OptionalUtils.mapOrElse(
                componentDefinition.getActions(),
                curActionDefinitions -> CollectionUtils.map(curActionDefinitions, OpenAPIActionDefinitionWrapper::new),
                List.of());

            ComponentHandler componentHandlerWrapper = new ComponentHandlerWrapper(
                new OpenAPIComponentDefinitionWrapper(componentDefinition, actionDefinitions));

            componentTaskHandlerFactories.add(
                new ComponentDefinitionFactoryBeanDefinition(
                    componentHandlerWrapper,
                    CollectionUtils.map(
                        OptionalUtils.orElse(componentDefinition.getActions(), Collections.emptyList()),
                        actionDefinition -> new HandlerBeanDefinitionEntry(
                            actionDefinition.getName(),
                            getBeanDefinition(
                                componentDefinition.getName(), componentDefinition.getVersion(),
                                actionDefinition.getName())))));
        }

        return componentTaskHandlerFactories;
    }

    private BeanDefinition getBeanDefinition(String componentName, int componentVersion, String actionName) {
        return BeanDefinitionBuilder.genericBeanDefinition(DefaultComponentActionTaskHandler.class)
            .addConstructorArgValue(componentName)
            .addConstructorArgValue(componentVersion)
            .addConstructorArgValue(actionName)
            .addConstructorArgReference("actionDefinitionService")
            .getBeanDefinition();
    }

    private static class OpenAPIActionDefinitionWrapper extends ActionDefinitionWrapper {

        public OpenAPIActionDefinitionWrapper(ActionDefinition actionDefinition) {
            super(actionDefinition);
        }

        @Override
        public Optional<PerformFunction> getPerform() {
            return Optional.of(
                (inputParameters, context) -> OpenApiClientUtils.execute(
                    inputParameters, properties == null ? List.of() : properties, outputSchema, metadata));
        }
    }

    private static class OpenAPIComponentDefinitionWrapper extends ComponentDefinitionWrapper {

        private final List<? extends ActionDefinition> actionDefinitions;

        public OpenAPIComponentDefinitionWrapper(
            ComponentDefinition componentDefinition, List<? extends ActionDefinition> actionDefinitions) {

            super(componentDefinition);

            this.actionDefinitions = actionDefinitions;
        }

        @Override
        public Optional<List<? extends ActionDefinition>> getActions() {
            return Optional.of(actionDefinitions);
        }
    }
}