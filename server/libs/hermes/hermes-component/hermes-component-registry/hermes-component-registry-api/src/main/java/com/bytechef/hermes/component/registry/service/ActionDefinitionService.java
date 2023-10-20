
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

package com.bytechef.hermes.component.registry.service;

import com.bytechef.hermes.component.registry.domain.ActionDefinition;
import com.bytechef.hermes.component.registry.ComponentOperation;
import com.bytechef.hermes.registry.domain.Option;
import com.bytechef.hermes.registry.domain.ValueProperty;

import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public interface ActionDefinitionService {

    List<? extends ValueProperty<?>> executeDynamicProperties(
        String componentName, int componentVersion, String actionName, String propertyName,
        Map<String, Object> actionParameters, Long connectionId, Map<String, ?> connectionParameters,
        String authorizationName);

    String executeEditorDescription(
        String componentName, int componentVersion, String actionName, Map<String, ?> actionParameters,
        Long connectionId, Map<String, ?> connectionParameters, String authorizationName);

    List<Option> executeOptions(
        String componentName, int componentVersion, String actionName, String propertyName,
        Map<String, Object> actionParameters, String searchText, Long connectionId, Map<String, ?> connectionParameters,
        String authorizationName);

    List<? extends ValueProperty<?>> executeOutputSchema(
        String componentName, int componentVersion, String actionName, Map<String, Object> actionParameters,
        Long connectionId, Map<String, ?> connectionParameters, String authorizationName);

    Object executePerform(
        String componentName, int componentVersion, String actionName, long taskExecutionId,
        Map<String, ?> inputParameters, Map<String, Long> connectionIdMap);

    Object executeSampleOutput(
        String componentName, int componentVersion, String actionName, Map<String, Object> actionParameters,
        Long connectionId, Map<String, ?> connectionParameters, String authorizationName);

    ActionDefinition getActionDefinition(String componentName, int componentVersion, String actionName);

    List<ActionDefinition> getActionDefinitions(String componentName, int componentVersion);

    List<ActionDefinition> getActionDefinitions(List<ComponentOperation> componentOperations);
}