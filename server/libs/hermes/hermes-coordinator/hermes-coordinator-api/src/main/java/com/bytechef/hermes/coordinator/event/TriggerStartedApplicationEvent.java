
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

package com.bytechef.hermes.coordinator.event;

import com.bytechef.hermes.coordinator.message.route.CoordinatorMessageRoute;

/**
 * @author Ivica Cardic
 */
public class TriggerStartedApplicationEvent extends AbstractEvent implements ApplicationEvent {

    private long triggerExecutionId;

    private TriggerStartedApplicationEvent() {
    }

    public TriggerStartedApplicationEvent(long triggerExecutionId) {
        super(CoordinatorMessageRoute.APPLICATION_EVENTS);

        this.triggerExecutionId = triggerExecutionId;
    }

    public long getTriggerExecutionId() {
        return triggerExecutionId;
    }

    @Override
    public String toString() {
        return "TriggerStartedEvent{" +
            "triggerExecutionId=" + triggerExecutionId +
            ", createdDate=" + createDate +
            ", route=" + route +
            "} ";
    }
}