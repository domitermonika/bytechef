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
import type { ControlTypeModel } from './ControlTypeModel';
import {
    ControlTypeModelFromJSON,
    ControlTypeModelFromJSONTyped,
    ControlTypeModelToJSON,
} from './ControlTypeModel';
import type { OptionModel } from './OptionModel';
import {
    OptionModelFromJSON,
    OptionModelFromJSONTyped,
    OptionModelToJSON,
} from './OptionModel';
import type { OptionsDataSourceModel } from './OptionsDataSourceModel';
import {
    OptionsDataSourceModelFromJSON,
    OptionsDataSourceModelFromJSONTyped,
    OptionsDataSourceModelToJSON,
} from './OptionsDataSourceModel';
import type { PropertyModel } from './PropertyModel';
import {
    PropertyModelFromJSON,
    PropertyModelFromJSONTyped,
    PropertyModelToJSON,
} from './PropertyModel';
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
 * An object property type.
 * @export
 * @interface ObjectPropertyModel
 */
export interface ObjectPropertyModel extends ValuePropertyModel {
    /**
     * Types of dynamically defined properties.
     * @type {Array<PropertyModel>}
     * @memberof ObjectPropertyModel
     */
    additionalProperties?: Array<PropertyModel>;
    /**
     * If the object can contain multiple additional properties.
     * @type {boolean}
     * @memberof ObjectPropertyModel
     */
    multipleValues?: boolean;
    /**
     * The object type.
     * @type {string}
     * @memberof ObjectPropertyModel
     */
    objectType?: string;
    /**
     * The list of valid property options.
     * @type {Array<OptionModel>}
     * @memberof ObjectPropertyModel
     */
    options?: Array<OptionModel>;
    /**
     * 
     * @type {OptionsDataSourceModel}
     * @memberof ObjectPropertyModel
     */
    optionsDataSource?: OptionsDataSourceModel;
    /**
     * The list of valid object property types.
     * @type {Array<PropertyModel>}
     * @memberof ObjectPropertyModel
     */
    properties?: Array<PropertyModel>;
}

/**
 * Check if a given object implements the ObjectPropertyModel interface.
 */
export function instanceOfObjectPropertyModel(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function ObjectPropertyModelFromJSON(json: any): ObjectPropertyModel {
    return ObjectPropertyModelFromJSONTyped(json, false);
}

export function ObjectPropertyModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): ObjectPropertyModel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        ...ValuePropertyModelFromJSONTyped(json, ignoreDiscriminator),
        'additionalProperties': !exists(json, 'additionalProperties') ? undefined : ((json['additionalProperties'] as Array<any>).map(PropertyModelFromJSON)),
        'multipleValues': !exists(json, 'multipleValues') ? undefined : json['multipleValues'],
        'objectType': !exists(json, 'objectType') ? undefined : json['objectType'],
        'options': !exists(json, 'options') ? undefined : ((json['options'] as Array<any>).map(OptionModelFromJSON)),
        'optionsDataSource': !exists(json, 'optionsDataSource') ? undefined : OptionsDataSourceModelFromJSON(json['optionsDataSource']),
        'properties': !exists(json, 'properties') ? undefined : ((json['properties'] as Array<any>).map(PropertyModelFromJSON)),
    };
}

export function ObjectPropertyModelToJSON(value?: ObjectPropertyModel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        ...ValuePropertyModelToJSON(value),
        'additionalProperties': value.additionalProperties === undefined ? undefined : ((value.additionalProperties as Array<any>).map(PropertyModelToJSON)),
        'multipleValues': value.multipleValues,
        'objectType': value.objectType,
        'options': value.options === undefined ? undefined : ((value.options as Array<any>).map(OptionModelToJSON)),
        'optionsDataSource': OptionsDataSourceModelToJSON(value.optionsDataSource),
        'properties': value.properties === undefined ? undefined : ((value.properties as Array<any>).map(PropertyModelToJSON)),
    };
}

