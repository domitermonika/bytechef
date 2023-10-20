
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

package com.bytechef.hermes.connection.remote.web.rest.service;

import com.bytechef.hermes.connection.domain.Connection;
import com.bytechef.hermes.connection.service.ConnectionService;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ivica Cardic
 */
@RestController
@RequestMapping("${openapi.openAPIDefinition.base-path:}/internal")
public class ConnectionServiceController {

    private final ConnectionService connectionService;

    @SuppressFBWarnings("EI")
    public ConnectionServiceController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/connection-service/get-connection/{id}",
        produces = {
            "application/json"
        })
    public ResponseEntity<Connection> getConnection(@PathVariable long id) {
        return ResponseEntity.ok(connectionService.getConnection(id));
    }

    @RequestMapping(
        method = RequestMethod.GET,
        value = "/connection-service/get-connections",
        produces = {
            "application/json"
        })
    public ResponseEntity<List<Connection>> getConnections() {
        return ResponseEntity.ok(connectionService.getConnections());
    }
}