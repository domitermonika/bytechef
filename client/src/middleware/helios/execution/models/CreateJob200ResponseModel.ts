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
 * 
 * @export
 * @interface CreateJob200ResponseModel
 */
export interface CreateJob200ResponseModel {
    /**
     * The id of an executed job.
     * @type {number}
     * @memberof CreateJob200ResponseModel
     */
    jobId?: number;
}

/**
 * Check if a given object implements the CreateJob200ResponseModel interface.
 */
export function instanceOfCreateJob200ResponseModel(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function CreateJob200ResponseModelFromJSON(json: any): CreateJob200ResponseModel {
    return CreateJob200ResponseModelFromJSONTyped(json, false);
}

export function CreateJob200ResponseModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): CreateJob200ResponseModel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'jobId': !exists(json, 'jobId') ? undefined : json['jobId'],
    };
}

export function CreateJob200ResponseModelToJSON(value?: CreateJob200ResponseModel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'jobId': value.jobId,
    };
}

