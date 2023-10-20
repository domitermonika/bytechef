
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

package com.bytechef.atlas.execution.remote.web.rest.service;

import com.bytechef.atlas.execution.domain.TaskExecution;
import com.bytechef.atlas.execution.service.RemoteTaskExecutionService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ivica Cardic
 */
@Hidden
@RestController
@RequestMapping("/remote/task-execution-service")
public class RemoteTaskExecutionServiceController {

    private final RemoteTaskExecutionService taskExecutionService;

    @SuppressFBWarnings("EI")
    public RemoteTaskExecutionServiceController(RemoteTaskExecutionService taskExecutionService) {
        this.taskExecutionService = taskExecutionService;
    }

    @RequestMapping(
        method = RequestMethod.POST,
        value = "/create",
        consumes = {
            "application/json"
        },
        produces = {
            "application/json"
        })
    public ResponseEntity<TaskExecution> create(@RequestBody TaskExecution taskExecution) {
        return ResponseEntity.ok(taskExecutionService.create(taskExecution));
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/get-task-execution/{id}",
        produces = {
            "application/json"
        })
    public ResponseEntity<TaskExecution> getTaskExecution(@PathVariable long id) {
        return ResponseEntity.ok(taskExecutionService.getTaskExecution(id));
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/get-job-task-executions/{jobId}",
        produces = {
            "application/json"
        })
    public ResponseEntity<List<TaskExecution>> getJobTaskExecutions(@PathVariable long jobId) {
        return ResponseEntity.ok(taskExecutionService.getJobTaskExecutions(jobId));
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/get-parent-task-executions/{parentId}",
        produces = {
            "application/json"
        })
    public ResponseEntity<List<TaskExecution>> getParentTaskExecutions(@PathVariable long parentId) {
        return ResponseEntity.ok(taskExecutionService.getParentTaskExecutions(parentId));
    }

    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/update",
        consumes = {
            "application/json"
        },
        produces = {
            "application/json"
        })
    public ResponseEntity<TaskExecution> update(@RequestBody TaskExecution taskExecution) {
        return ResponseEntity.ok(taskExecutionService.update(taskExecution));
    }
}