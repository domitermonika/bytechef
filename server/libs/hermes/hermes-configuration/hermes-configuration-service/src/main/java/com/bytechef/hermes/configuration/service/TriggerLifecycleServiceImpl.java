
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

package com.bytechef.hermes.configuration.service;

import com.bytechef.hermes.component.definition.TriggerDefinition.DynamicWebhookEnableOutput;
import com.bytechef.hermes.configuration.domain.TriggerLifecycle;
import com.bytechef.hermes.configuration.repository.TriggerLifecycleRepository;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Ivica Cardic
 */
@Service
@Transactional
public class TriggerLifecycleServiceImpl implements TriggerLifecycleService {

    private final TriggerLifecycleRepository triggerLifecycleRepository;

    @SuppressFBWarnings("EI")
    public TriggerLifecycleServiceImpl(TriggerLifecycleRepository triggerLifecycleRepository) {
        this.triggerLifecycleRepository = triggerLifecycleRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public <T> Optional<T> fetchValue(String workflowExecutionId) {
        return triggerLifecycleRepository.findByWorkflowExecutionId(workflowExecutionId)
            .map(dataStorage -> (T) dataStorage.getValue());
    }

    @Override
    public void save(String workflowExecutionId, DynamicWebhookEnableOutput value) {
        triggerLifecycleRepository
            .findByWorkflowExecutionId(workflowExecutionId)
            .ifPresentOrElse(
                triggerLifecycle -> {
                    triggerLifecycle.setValue(value);

                    triggerLifecycleRepository.save(triggerLifecycle);
                },
                () -> triggerLifecycleRepository.save(new TriggerLifecycle(workflowExecutionId, value)));
    }
}