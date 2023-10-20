
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

package com.bytechef.hermes.definition.registry.task.dispatcher;

import com.bytechef.hermes.task.dispatcher.definition.TaskDispatcherDefinition;

import java.util.List;

public interface TaskDispatcherDefinitionRegistry {

    TaskDispatcherDefinition getTaskDispatcherDefinition(String name, Integer version);

    List<TaskDispatcherDefinition> getTaskDispatcherDefinitions();

    List<TaskDispatcherDefinition> getTaskDispatcherDefinitions(String name);
}