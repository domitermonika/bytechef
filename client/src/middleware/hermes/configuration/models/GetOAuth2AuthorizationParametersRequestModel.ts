/* tslint:disable */
/* eslint-disable */
/**
 * The Definition API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { exists, mapValues } from '../runtime';
/**
 * Contains all required information to open a connection to a service defined by componentName parameter.
 * @export
 * @interface GetOAuth2AuthorizationParametersRequestModel
 */
export interface GetOAuth2AuthorizationParametersRequestModel {
    /**
     * The name of an authorization used by this connection. Used for HTTP based services.
     * @type {string}
     * @memberof GetOAuth2AuthorizationParametersRequestModel
     */
    authorizationName?: string;
    /**
     * The name of a component that uses this connection.
     * @type {string}
     * @memberof GetOAuth2AuthorizationParametersRequestModel
     */
    componentName: string;
    /**
     * The version of a connection.
     * @type {number}
     * @memberof GetOAuth2AuthorizationParametersRequestModel
     */
    connectionVersion?: number;
    /**
     * The parameters of a connection.
     * @type {{ [key: string]: object; }}
     * @memberof GetOAuth2AuthorizationParametersRequestModel
     */
    parameters: { [key: string]: object; };
}

/**
 * Check if a given object implements the GetOAuth2AuthorizationParametersRequestModel interface.
 */
export function instanceOfGetOAuth2AuthorizationParametersRequestModel(value: object): boolean {
    let isInstance = true;
    isInstance = isInstance && "componentName" in value;
    isInstance = isInstance && "parameters" in value;

    return isInstance;
}

export function GetOAuth2AuthorizationParametersRequestModelFromJSON(json: any): GetOAuth2AuthorizationParametersRequestModel {
    return GetOAuth2AuthorizationParametersRequestModelFromJSONTyped(json, false);
}

export function GetOAuth2AuthorizationParametersRequestModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): GetOAuth2AuthorizationParametersRequestModel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'authorizationName': !exists(json, 'authorizationName') ? undefined : json['authorizationName'],
        'componentName': json['componentName'],
        'connectionVersion': !exists(json, 'connectionVersion') ? undefined : json['connectionVersion'],
        'parameters': json['parameters'],
    };
}

export function GetOAuth2AuthorizationParametersRequestModelToJSON(value?: GetOAuth2AuthorizationParametersRequestModel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'authorizationName': value.authorizationName,
        'componentName': value.componentName,
        'connectionVersion': value.connectionVersion,
        'parameters': value.parameters,
    };
}

