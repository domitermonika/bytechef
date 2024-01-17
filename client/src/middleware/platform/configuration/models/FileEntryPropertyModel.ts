/* tslint:disable */
/* eslint-disable */
/**
 * The Platform Configuration API
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
import type { ControlTypeModel } from './ControlTypeModel';
import {
    ControlTypeModelFromJSON,
    ControlTypeModelFromJSONTyped,
    ControlTypeModelToJSON,
} from './ControlTypeModel';
import type { PropertyTypeModel } from './PropertyTypeModel';
import {
    PropertyTypeModelFromJSON,
    PropertyTypeModelFromJSONTyped,
    PropertyTypeModelToJSON,
} from './PropertyTypeModel';
import type { ValuePropertyModel } from './ValuePropertyModel';
import {
    ValuePropertyModelFromJSON,
    ValuePropertyModelFromJSONTyped,
    ValuePropertyModelToJSON,
} from './ValuePropertyModel';

/**
 * An file entry property type.
 * @export
 * @interface FileEntryPropertyModel
 */
export interface FileEntryPropertyModel extends ValuePropertyModel {
    /**
     * The list of valid file entry property types.
     * @type {Array<ValuePropertyModel>}
     * @memberof FileEntryPropertyModel
     */
    properties?: Array<ValuePropertyModel>;
}

/**
 * Check if a given object implements the FileEntryPropertyModel interface.
 */
export function instanceOfFileEntryPropertyModel(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function FileEntryPropertyModelFromJSON(json: any): FileEntryPropertyModel {
    return FileEntryPropertyModelFromJSONTyped(json, false);
}

export function FileEntryPropertyModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): FileEntryPropertyModel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        ...ValuePropertyModelFromJSONTyped(json, ignoreDiscriminator),
        'properties': !exists(json, 'properties') ? undefined : ((json['properties'] as Array<any>).map(ValuePropertyModelFromJSON)),
    };
}

export function FileEntryPropertyModelToJSON(value?: FileEntryPropertyModel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        ...ValuePropertyModelToJSON(value),
        'properties': value.properties === undefined ? undefined : ((value.properties as Array<any>).map(ValuePropertyModelToJSON)),
    };
}
