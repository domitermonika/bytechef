
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

package com.bytechef.hermes.definition.registry.component.action;

import com.bytechef.commons.util.OptionalUtils;
import com.bytechef.hermes.component.ActionContext;
import com.bytechef.hermes.component.InputParameters;
import com.bytechef.hermes.component.definition.ActionDefinition;
import com.bytechef.hermes.component.definition.ComponentDSL;
import com.bytechef.hermes.component.definition.ComponentDSL.ModifiableActionDefinition;
import com.bytechef.hermes.component.definition.ComponentDefinition;
import com.bytechef.hermes.component.util.HttpClientUtils;
import com.bytechef.hermes.definition.Property.ControlType;
import com.bytechef.hermes.definition.Property.StringProperty.SampleDataType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bytechef.hermes.component.definition.ComponentDSL.fileEntry;
import static com.bytechef.hermes.component.util.HttpClientUtils.responseFormat;
import static com.bytechef.hermes.definition.DefinitionDSL.object;
import static com.bytechef.hermes.definition.DefinitionDSL.oneOf;
import static com.bytechef.hermes.definition.DefinitionDSL.option;
import static com.bytechef.hermes.definition.DefinitionDSL.string;

/**
 * @author Ivica Cardic
 */
public class CustomAction {

    public static final String CUSTOM = "custom";

    private static final String BODY_CONTENT = "bodyContent";
    private static final String BODY_CONTENT_MIME_TYPE = "bodyContentMimeType";
    private static final String BODY_CONTENT_TYPE = "bodyContentType";
    private static final String HEADERS = "headers";
    private static final String METHOD = "method";
    private static final String QUERY_PARAMETERS = "queryParameters";
    private static final String OUTPUT_SCHEMA = "outputSchema";
    private static final String PATH = "path";

    private enum BodyContentType {
        FORM_DATA,
        FORM_URL_ENCODED,
        JSON,
        RAW,
        XML
    }

    private enum RequestMethod {
        DELETE,
        GET,
        PATCH,
        POST,
        PUT,
    }

    private static final ModifiableActionDefinition CUSTOM_ACTION_DEFINITION = ComponentDSL.action(CUSTOM)
        .title("Custom Action")
        .description(
            "By using custom actions, you can take advantage of the existing connector infrastructure to create new actions.")
        .properties(
            string(PATH)
                .label("Path")
                .description(
                    "The relative URI that will be appended to the end of the base URI. Do not prepend '/' in your relative URL.")
                .required(true),
            string(METHOD)
                .label("Method")
                .description("The http method.")
                .options(
                    option(RequestMethod.DELETE.name(), RequestMethod.DELETE),
                    option(RequestMethod.GET.name(), RequestMethod.GET),
                    option(RequestMethod.PATCH.name(), RequestMethod.PATCH),
                    option(RequestMethod.POST.name(), RequestMethod.POST),
                    option(RequestMethod.PUT.name(), RequestMethod.PUT))
                .required(true)
                .defaultValue(RequestMethod.GET.name()),

            //
            // Header parameters properties
            //

            object(HEADERS)
                .label("Headers")
                .description("Headers to send.")
                .placeholder("Add header")
                .additionalProperties(string()),

            //
            // Query parameters properties
            //

            object(QUERY_PARAMETERS)
                .label("Query Parameters")
                .description("Query parameters to send.")
                .placeholder("Add parameter")
                .additionalProperties(string()),

            //
            // Body properties
            //

            string(BODY_CONTENT_TYPE)
                .label("Body Content Type")
                .description("Content-Type to use when sending body parameters.")
                .displayCondition(
                    "['%s','%s','%s'].includes('%s')".formatted(
                        RequestMethod.PATCH.name(), RequestMethod.POST.name(), RequestMethod.PUT.name(), METHOD))
                .options(
                    option("None", ""),
                    option("JSON", HttpClientUtils.BodyContentType.JSON.name()),
                    option("Form-Data", HttpClientUtils.BodyContentType.FORM_DATA.name()),
                    option("Form-Urlencoded", HttpClientUtils.BodyContentType.FORM_URL_ENCODED.name()),
                    option("Raw", HttpClientUtils.BodyContentType.RAW.name()))
                .defaultValue(""),

            object(BODY_CONTENT)
                .label("Body Content - JSON")
                .description("Body Parameters to send.")
                .displayCondition("%s === '%s'".formatted(BODY_CONTENT_TYPE, BodyContentType.JSON.name()))
                .additionalProperties(oneOf())
                .placeholder("Add Parameter"),
            object(BODY_CONTENT)
                .label("Body Content - XML")
                .description("XML content to send.")
                .displayCondition("%s === '%s'".formatted(BODY_CONTENT_TYPE, BodyContentType.XML.name()))
                .placeholder("Add Parameter"),
            object(BODY_CONTENT)
                .label("Body Content - Form Data")
                .description("Body parameters to send.")
                .displayCondition("%s === '%s'".formatted(BODY_CONTENT_TYPE, BodyContentType.FORM_DATA.name()))
                .placeholder("Add Parameter")
                .additionalProperties(oneOf().types(string(), fileEntry())),
            object(BODY_CONTENT)
                .label("Body Content - Form URL-Encoded")
                .description("Body parameters to send.")
                .displayCondition("%s === '%s'".formatted(BODY_CONTENT_TYPE, BodyContentType.FORM_URL_ENCODED.name()))
                .placeholder("Add Parameter")
                .additionalProperties(string()),
            string(BODY_CONTENT)
                .label("Body Content - Raw")
                .description("The raw text to send.")
                .displayCondition("%s === '%s'".formatted(BODY_CONTENT_TYPE, BodyContentType.RAW.name())),

            string(BODY_CONTENT_MIME_TYPE)
                .label("Content Type")
                .description("Mime-Type to use when sending raw body content.")
                .displayCondition(
                    "'%s' === '%s'".formatted(
                        HttpClientUtils.BodyContentType.RAW.name(), BODY_CONTENT_TYPE))
                .defaultValue("text/plain")
                .placeholder("text/plain"),

            //
            // TODO Add support for using it in the editor
            //

            string(OUTPUT_SCHEMA)
                .label("Output Schema")
                .description(
                    "Please provide a description of the desired format for the API's output schema. This format will then be utilized to generate the data tree for the output.")
                .controlType(ControlType.SCHEMA_DESIGNER)
                .sampleDataType(SampleDataType.JSON))
        .outputSchema(oneOf())
        .execute(CustomAction::execute);

    public static ActionDefinition getCustomActionDefinition(ComponentDefinition componentDefinition) {
        return CUSTOM_ACTION_DEFINITION.help(OptionalUtils.orElse(componentDefinition.getCustomActionHelp(), null));
    }

    protected static Object execute(ActionContext context, InputParameters inputParameters) {
        return HttpClientUtils.exchange(
            inputParameters.getRequiredString(PATH),
            inputParameters.getRequired(METHOD, HttpClientUtils.RequestMethod.class))
            .configuration(responseFormat(HttpClientUtils.ResponseFormat.JSON))
            .body(getBody(inputParameters.get(BODY_CONTENT_TYPE, BodyContentType.class), inputParameters))
            .headers(
                inputParameters.getMap(HEADERS, Map.of())
                    .entrySet()
                    .stream()
                    .collect(
                        Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> List.of((String) entry.getValue()))))
            .queryParameters(
                inputParameters.getMap(QUERY_PARAMETERS, Map.of())
                    .entrySet()
                    .stream()
                    .collect(
                        Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> List.of((String) entry.getValue()))))
            .execute();
    }

    private static HttpClientUtils.Body getBody(BodyContentType bodyContentType, InputParameters inputParameters) {
        HttpClientUtils.Body body = null;

        if (bodyContentType != null) {
            if (bodyContentType == BodyContentType.RAW) {
                body = HttpClientUtils.Body.of(inputParameters.getRequiredString(BODY_CONTENT));
            } else {
                HttpClientUtils.Body.of(inputParameters.getRequiredMap(BODY_CONTENT));
            }
        }

        return body;
    }
}