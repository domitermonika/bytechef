
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

package com.bytechef.hermes.workflow.remote.client.factory;

import com.bytechef.atlas.dto.JobParameters;
import com.bytechef.atlas.factory.JobFactory;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Ivica Cardic
 */
@Component
public class JobFactoryClient implements JobFactory {

    private final WebClient.Builder loadBalancedWebClientBuilder;

    @SuppressFBWarnings("EI")
    public JobFactoryClient(WebClient.Builder loadBalancedWebClientBuilder) {
        this.loadBalancedWebClientBuilder = loadBalancedWebClientBuilder;
    }

    @Override
    @SuppressFBWarnings("NP")
    public long create(JobParameters jobParameters) {
        return loadBalancedWebClientBuilder
            .build()
            .post()
            .uri(uriBuilder -> uriBuilder
                .host("platform-service-app")
                .path("/api/internal/job-factory/create")
                .build())
            .bodyValue(jobParameters)
            .retrieve()
            .bodyToMono(Long.class)
            .block();
    }
}