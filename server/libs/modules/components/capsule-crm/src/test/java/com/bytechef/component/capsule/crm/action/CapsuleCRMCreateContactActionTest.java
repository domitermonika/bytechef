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

package com.bytechef.component.capsule.crm.action;

import static com.bytechef.component.capsule.crm.constant.CapsuleCRMConstants.ABOUT;
import static com.bytechef.component.capsule.crm.constant.CapsuleCRMConstants.ADDRESSES;
import static com.bytechef.component.capsule.crm.constant.CapsuleCRMConstants.EMAIL_ADDRESSES;
import static com.bytechef.component.capsule.crm.constant.CapsuleCRMConstants.FIRST_NAME;
import static com.bytechef.component.capsule.crm.constant.CapsuleCRMConstants.LAST_NAME;
import static com.bytechef.component.capsule.crm.constant.CapsuleCRMConstants.PHONE_NUMBERS;
import static com.bytechef.component.capsule.crm.constant.CapsuleCRMConstants.TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.bytechef.component.definition.Context.Http;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author Monika Domiter
 */
class CapsuleCRMCreateContactActionTest extends AbstractCapsuleCRMActionTest {

    @Test
    void testPerform() {
        when(mockedParameters.getString(FIRST_NAME))
            .thenReturn("fname");
        when(mockedParameters.getString(LAST_NAME))
            .thenReturn("lname");
        when(mockedParameters.getRequiredString(TYPE))
            .thenReturn("type");
        when(mockedParameters.getString(ABOUT))
            .thenReturn("about");
        when(mockedParameters.getList(EMAIL_ADDRESSES))
            .thenReturn(null);
        when(mockedParameters.getList(ADDRESSES))
            .thenReturn(null);
        when(mockedParameters.getList(PHONE_NUMBERS))
            .thenReturn(null);

        Object result = CapsuleCRMCreateContactAction.perform(mockedParameters, mockedParameters, mockedContext);

        assertEquals(responeseMap, result);

        Http.Body body = bodyArgumentCaptor.getValue();

        assertEquals(Map.of("party",
            Map.of(
            TYPE, "type",
            FIRST_NAME, "fname",
            LAST_NAME, "lname",
            ABOUT, "about")), body.getContent());
    }

}
