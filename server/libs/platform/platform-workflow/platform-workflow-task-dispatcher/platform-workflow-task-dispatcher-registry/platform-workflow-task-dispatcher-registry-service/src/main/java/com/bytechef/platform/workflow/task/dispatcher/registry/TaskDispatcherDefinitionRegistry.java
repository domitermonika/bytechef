/*
 * Copyright 2023-present ByteChef Inc.
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

package com.bytechef.platform.workflow.task.dispatcher.registry;

import com.bytechef.commons.util.CollectionUtils;
import com.bytechef.commons.util.OptionalUtils;
import com.bytechef.platform.util.PropertyUtils;
import com.bytechef.platform.workflow.task.dispatcher.TaskDispatcherDefinitionFactory;
import com.bytechef.platform.workflow.task.dispatcher.definition.OutputSchema;
import com.bytechef.platform.workflow.task.dispatcher.definition.Property;
import com.bytechef.platform.workflow.task.dispatcher.definition.TaskDispatcherDefinition;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * @author Ivica Cardic
 */
@Component
public class TaskDispatcherDefinitionRegistry {

    private final List<TaskDispatcherDefinition> taskDispatcherDefinitions;

    @SuppressFBWarnings("EI")
    public TaskDispatcherDefinitionRegistry(
        List<TaskDispatcherDefinitionFactory> taskDispatcherDefinitionFactories) {

        this.taskDispatcherDefinitions = taskDispatcherDefinitionFactories.stream()
            .map(TaskDispatcherDefinitionFactory::getDefinition)
            .sorted((o1, o2) -> {
                String o1Name = o1.getName();

                return o1Name.compareTo(o2.getName());
            })
            .toList();

        // Validate

        validate(taskDispatcherDefinitions);
    }

    public TaskDispatcherDefinition getTaskDispatcherDefinition(String name, Integer version) {
        return taskDispatcherDefinitions.stream()
            .filter(taskDispatcherDefinition -> name.equalsIgnoreCase(taskDispatcherDefinition.getName()) &&
                version == taskDispatcherDefinition.getVersion())
            .findFirst()
            .orElseThrow(IllegalStateException::new);
    }

    @SuppressFBWarnings("EI")
    public List<TaskDispatcherDefinition> getTaskDispatcherDefinitions() {
        return taskDispatcherDefinitions;
    }

    public List<TaskDispatcherDefinition> getTaskDispatcherDefinitions(String name) {
        return CollectionUtils.filter(
            taskDispatcherDefinitions,
            taskDispatcherDefinition -> Objects.equals(taskDispatcherDefinition.getName(), name));
    }

    private void validate(List<TaskDispatcherDefinition> taskDispatcherDefinitions) {
        for (TaskDispatcherDefinition taskDispatcherDefinition : taskDispatcherDefinitions) {
            PropertyUtils.checkInputProperties(
                CollectionUtils.map(
                    OptionalUtils.orElse(taskDispatcherDefinition.getProperties(), List.of()), Property::getName));
            PropertyUtils.checkOutputProperty(
                OptionalUtils.mapOrElse(
                    OptionalUtils.mapOptional(taskDispatcherDefinition.getOutputSchema(), OutputSchema::definition),
                    Property::getName, null));
        }
    }
}