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

package com.bytechef.helios.configuration.web.rest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.bytechef.atlas.configuration.domain.Workflow;
import com.bytechef.atlas.configuration.service.WorkflowService;
import com.bytechef.helios.configuration.facade.ProjectFacade;
import com.bytechef.helios.configuration.facade.ProjectInstanceFacade;
import com.bytechef.helios.configuration.web.rest.config.ProjectConfigurationRestTestConfiguration;
import com.bytechef.hermes.configuration.facade.WorkflowConnectionFacade;
import com.bytechef.hermes.configuration.web.rest.model.WorkflowModel;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

/**
 * @author Ivica Cardic
 */
@ContextConfiguration(classes = ProjectConfigurationRestTestConfiguration.class)
@WebMvcTest(WorkflowApiController.class)
public class WorkflowApiControllerIntTest {

    public static final String DEFINITION = """
        {
            "label": "label",
            "tasks": [
                {
                    "name": "name",
                    "type": "type"
                }
            ]
        }
        """;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectInstanceFacade projectInstanceFacade;

    @MockBean
    private ProjectFacade projectFacade;

    private WebTestClient webTestClient;

    @MockBean
    private WorkflowService workflowService;

    @MockBean
    private WorkflowConnectionFacade workflowConnectionFacade;

    @BeforeEach
    public void setup() {
        this.webTestClient = MockMvcWebTestClient
            .bindTo(mockMvc)
            .build();
    }

    @Test
    public void testGetWorkflows() {
        when(workflowService.getWorkflows(anyInt()))
            .thenReturn(List.of(getWorkflow()));

        try {
            this.webTestClient
                .get()
                .uri("/workflows")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(WorkflowModel.class)
                .hasSize(1);
        } catch (Exception exception) {
            Assertions.fail(exception);
        }
    }

    private Workflow getWorkflow() {
        return new Workflow("1", DEFINITION, Workflow.Format.JSON, 0);
    }
}
