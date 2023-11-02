/* tslint:disable */
/* eslint-disable */
/**
 * The Automation Execution API
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
 * The connection used in a particular task.
 * @export
 * @interface TaskConnectionModel
 */
export interface TaskConnectionModel {
    /**
     * The connection id
     * @type {number}
     * @memberof TaskConnectionModel
     */
    id?: number;
    /**
     * The connection key under which a connection is defined in a workflow definition.
     * @type {string}
     * @memberof TaskConnectionModel
     */
    key?: string;
    /**
     * The task name to which a connection belongs.
     * @type {string}
     * @memberof TaskConnectionModel
     */
    taskName?: string;
}

/**
 * Check if a given object implements the TaskConnectionModel interface.
 */
export function instanceOfTaskConnectionModel(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function TaskConnectionModelFromJSON(json: any): TaskConnectionModel {
    return TaskConnectionModelFromJSONTyped(json, false);
}

export function TaskConnectionModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): TaskConnectionModel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'id': !exists(json, 'id') ? undefined : json['id'],
        'key': !exists(json, 'key') ? undefined : json['key'],
        'taskName': !exists(json, 'taskName') ? undefined : json['taskName'],
    };
}

export function TaskConnectionModelToJSON(value?: TaskConnectionModel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'id': value.id,
        'key': value.key,
        'taskName': value.taskName,
    };
}

