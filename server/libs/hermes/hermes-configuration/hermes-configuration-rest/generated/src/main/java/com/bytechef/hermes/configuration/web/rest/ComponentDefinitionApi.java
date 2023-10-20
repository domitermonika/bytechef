/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.bytechef.hermes.configuration.web.rest;

import com.bytechef.hermes.configuration.web.rest.model.ComponentDefinitionBasicModel;
import com.bytechef.hermes.configuration.web.rest.model.ComponentDefinitionModel;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-03T14:12:00.337960+02:00[Europe/Zagreb]")
@Validated
@Tag(name = "component-definition", description = "The Core Component Definition API")
public interface ComponentDefinitionApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /component-definitions/{componentName} : Get a component definition
     * Get a component definition.
     *
     * @param componentName The name of a component to get. (required)
     * @param componentVersion The version of a component to get. If not set, teh latest version is returned. (optional)
     * @return Successful operation. (status code 200)
     */
    @Operation(
        operationId = "getComponentDefinition",
        summary = "Get a component definition",
        description = "Get a component definition.",
        tags = { "component-definition" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation.", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ComponentDefinitionModel.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/component-definitions/{componentName}",
        produces = { "application/json" }
    )
    default ResponseEntity<ComponentDefinitionModel> getComponentDefinition(
        @Parameter(name = "componentName", description = "The name of a component to get.", required = true, in = ParameterIn.PATH) @PathVariable("componentName") String componentName,
        @Parameter(name = "componentVersion", description = "The version of a component to get. If not set, teh latest version is returned.", in = ParameterIn.QUERY) @Valid @RequestParam(value = "componentVersion", required = false) Integer componentVersion
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"icon\" : \"icon\", \"name\" : \"name\", \"description\" : \"description\", \"resources\" : { \"documentationUrl\" : \"documentationUrl\" }, \"connection\" : { \"componentTitle\" : \"componentTitle\", \"componentDescription\" : \"componentDescription\", \"componentName\" : \"componentName\", \"version\" : 0 }, \"category\" : \"category\", \"title\" : \"title\", \"triggers\" : [ { \"help\" : { \"body\" : \"body\", \"learnMoreUrl\" : \"learnMoreUrl\" }, \"name\" : \"name\", \"description\" : \"description\", \"title\" : \"title\" }, { \"help\" : { \"body\" : \"body\", \"learnMoreUrl\" : \"learnMoreUrl\" }, \"name\" : \"name\", \"description\" : \"description\", \"title\" : \"title\" } ], \"actions\" : [ { \"help\" : { \"body\" : \"body\", \"learnMoreUrl\" : \"learnMoreUrl\" }, \"name\" : \"name\", \"description\" : \"description\", \"title\" : \"title\" }, { \"help\" : { \"body\" : \"body\", \"learnMoreUrl\" : \"learnMoreUrl\" }, \"name\" : \"name\", \"description\" : \"description\", \"title\" : \"title\" } ], \"version\" : 0, \"tags\" : [ \"tags\", \"tags\" ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /component-definitions/{componentName}/versions : Get all component definition versions of a component
     * Get all component definition versions of a component.
     *
     * @param componentName The name of a component. (required)
     * @return Successful operation. (status code 200)
     */
    @Operation(
        operationId = "getComponentDefinitionVersions",
        summary = "Get all component definition versions of a component",
        description = "Get all component definition versions of a component.",
        tags = { "component-definition" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation.", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ComponentDefinitionBasicModel.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/component-definitions/{componentName}/versions",
        produces = { "application/json" }
    )
    default ResponseEntity<List<ComponentDefinitionBasicModel>> getComponentDefinitionVersions(
        @Parameter(name = "componentName", description = "The name of a component.", required = true, in = ParameterIn.PATH) @PathVariable("componentName") String componentName
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"icon\" : \"icon\", \"name\" : \"name\", \"description\" : \"description\", \"resources\" : { \"documentationUrl\" : \"documentationUrl\" }, \"category\" : \"category\", \"title\" : \"title\", \"tags\" : [ \"tags\", \"tags\" ] }, { \"icon\" : \"icon\", \"name\" : \"name\", \"description\" : \"description\", \"resources\" : { \"documentationUrl\" : \"documentationUrl\" }, \"category\" : \"category\", \"title\" : \"title\", \"tags\" : [ \"tags\", \"tags\" ] } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /component-definitions : Get all component definitions
     * Get all component definitions.
     *
     * @param actionDefinitions Use for filtering components which define action definitions. (optional)
     * @param connectionDefinitions Use for filtering components which define connection definitions. (optional)
     * @param connectionInstances Use for filtering components which have connection instances created. (optional)
     * @param triggerDefinitions Use for filtering components which define trigger definitions. (optional)
     * @param include The list of component names to include in the result. (optional)
     * @return Successful operation. (status code 200)
     */
    @Operation(
        operationId = "getComponentDefinitions",
        summary = "Get all component definitions",
        description = "Get all component definitions.",
        tags = { "component-definition" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation.", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ComponentDefinitionBasicModel.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/component-definitions",
        produces = { "application/json" }
    )
    default ResponseEntity<List<ComponentDefinitionBasicModel>> getComponentDefinitions(
        @Parameter(name = "actionDefinitions", description = "Use for filtering components which define action definitions.", in = ParameterIn.QUERY) @Valid @RequestParam(value = "actionDefinitions", required = false) Boolean actionDefinitions,
        @Parameter(name = "connectionDefinitions", description = "Use for filtering components which define connection definitions.", in = ParameterIn.QUERY) @Valid @RequestParam(value = "connectionDefinitions", required = false) Boolean connectionDefinitions,
        @Parameter(name = "connectionInstances", description = "Use for filtering components which have connection instances created.", in = ParameterIn.QUERY) @Valid @RequestParam(value = "connectionInstances", required = false) Boolean connectionInstances,
        @Parameter(name = "triggerDefinitions", description = "Use for filtering components which define trigger definitions.", in = ParameterIn.QUERY) @Valid @RequestParam(value = "triggerDefinitions", required = false) Boolean triggerDefinitions,
        @Parameter(name = "include", description = "The list of component names to include in the result.", in = ParameterIn.QUERY) @Valid @RequestParam(value = "include", required = false) List<String> include
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"icon\" : \"icon\", \"name\" : \"name\", \"description\" : \"description\", \"resources\" : { \"documentationUrl\" : \"documentationUrl\" }, \"category\" : \"category\", \"title\" : \"title\", \"tags\" : [ \"tags\", \"tags\" ] }, { \"icon\" : \"icon\", \"name\" : \"name\", \"description\" : \"description\", \"resources\" : { \"documentationUrl\" : \"documentationUrl\" }, \"category\" : \"category\", \"title\" : \"title\", \"tags\" : [ \"tags\", \"tags\" ] } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}