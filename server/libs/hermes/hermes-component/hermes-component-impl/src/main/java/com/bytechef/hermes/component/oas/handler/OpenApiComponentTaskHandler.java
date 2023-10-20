
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

package com.bytechef.hermes.component.oas.handler;

import com.bytechef.atlas.execution.domain.TaskExecution;
import com.bytechef.atlas.worker.task.exception.TaskExecutionException;
import com.bytechef.hermes.component.OpenApiComponentHandler;
import com.bytechef.hermes.component.handler.ComponentTaskHandler;
import com.bytechef.hermes.component.util.HttpClientUtils.Response;
import com.bytechef.hermes.definition.registry.service.ActionDefinitionService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * @author Ivica Cardic
 */
public class OpenApiComponentTaskHandler extends ComponentTaskHandler {

    private final String actionName;
    private final OpenApiComponentHandler openApiComponentHandler;

    @SuppressFBWarnings("EI2")
    public OpenApiComponentTaskHandler(
        String actionName, ActionDefinitionService actionDefinitionService,
        OpenApiComponentHandler openApiComponentHandler) {

        super(
            openApiComponentHandler.getName(), openApiComponentHandler.getVersion(), actionName,
            actionDefinitionService);

        this.actionName = actionName;
        this.openApiComponentHandler = openApiComponentHandler;
    }

    @Override
    @SuppressFBWarnings("NP")
    public Object handle(TaskExecution taskExecution) throws TaskExecutionException {
        try {
            return openApiComponentHandler.postExecute(actionName, (Response) super.handle(taskExecution));
        } catch (Exception e) {
            throw new TaskExecutionException(e.getMessage(), e);
        }
    }
}