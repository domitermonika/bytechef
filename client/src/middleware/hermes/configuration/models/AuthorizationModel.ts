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
import type { AuthorizationTypeModel } from './AuthorizationTypeModel';
import {
    AuthorizationTypeModelFromJSON,
    AuthorizationTypeModelFromJSONTyped,
    AuthorizationTypeModelToJSON,
} from './AuthorizationTypeModel';
import type { PropertyModel } from './PropertyModel';
import {
    PropertyModelFromJSON,
    PropertyModelFromJSONTyped,
    PropertyModelToJSON,
} from './PropertyModel';

/**
 * Contains information required for a connection's authorization.
 * @export
 * @interface AuthorizationModel
 */
export interface AuthorizationModel {
    /**
     * The description.
     * @type {string}
     * @memberof AuthorizationModel
     */
    description?: string;
    /**
     * The authorization name.
     * @type {string}
     * @memberof AuthorizationModel
     */
    name?: string;
    /**
     * Properties of the connection.
     * @type {Array<PropertyModel>}
     * @memberof AuthorizationModel
     */
    properties?: Array<PropertyModel>;
    /**
     * The title
     * @type {string}
     * @memberof AuthorizationModel
     */
    title?: string;
    /**
     * 
     * @type {AuthorizationTypeModel}
     * @memberof AuthorizationModel
     */
    type?: AuthorizationTypeModel;
}

/**
 * Check if a given object implements the AuthorizationModel interface.
 */
export function instanceOfAuthorizationModel(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function AuthorizationModelFromJSON(json: any): AuthorizationModel {
    return AuthorizationModelFromJSONTyped(json, false);
}

export function AuthorizationModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): AuthorizationModel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'description': !exists(json, 'description') ? undefined : json['description'],
        'name': !exists(json, 'name') ? undefined : json['name'],
        'properties': !exists(json, 'properties') ? undefined : ((json['properties'] as Array<any>).map(PropertyModelFromJSON)),
        'title': !exists(json, 'title') ? undefined : json['title'],
        'type': !exists(json, 'type') ? undefined : AuthorizationTypeModelFromJSON(json['type']),
    };
}

export function AuthorizationModelToJSON(value?: AuthorizationModel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'description': value.description,
        'name': value.name,
        'properties': value.properties === undefined ? undefined : ((value.properties as Array<any>).map(PropertyModelToJSON)),
        'title': value.title,
        'type': AuthorizationTypeModelToJSON(value.type),
    };
}

