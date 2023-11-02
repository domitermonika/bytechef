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
 * The blueprint that describe the execution of a job.
 * @export
 * @interface WorkflowBasicModel
 */
export interface WorkflowBasicModel {
    /**
     * The created by.
     * @type {string}
     * @memberof WorkflowBasicModel
     */
    readonly createdBy?: string;
    /**
     * The created date.
     * @type {Date}
     * @memberof WorkflowBasicModel
     */
    readonly createdDate?: Date;
    /**
     * The description of a workflow.
     * @type {string}
     * @memberof WorkflowBasicModel
     */
    description?: string;
    /**
     * The id of the workflow.
     * @type {string}
     * @memberof WorkflowBasicModel
     */
    readonly id?: string;
    /**
     * The descriptive name for the workflow
     * @type {string}
     * @memberof WorkflowBasicModel
     */
    readonly label?: string;
    /**
     * The last modified by.
     * @type {string}
     * @memberof WorkflowBasicModel
     */
    readonly lastModifiedBy?: string;
    /**
     * The last modified date.
     * @type {Date}
     * @memberof WorkflowBasicModel
     */
    readonly lastModifiedDate?: Date;
}

/**
 * Check if a given object implements the WorkflowBasicModel interface.
 */
export function instanceOfWorkflowBasicModel(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function WorkflowBasicModelFromJSON(json: any): WorkflowBasicModel {
    return WorkflowBasicModelFromJSONTyped(json, false);
}

export function WorkflowBasicModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): WorkflowBasicModel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'createdBy': !exists(json, 'createdBy') ? undefined : json['createdBy'],
        'createdDate': !exists(json, 'createdDate') ? undefined : (new Date(json['createdDate'])),
        'description': !exists(json, 'description') ? undefined : json['description'],
        'id': !exists(json, 'id') ? undefined : json['id'],
        'label': !exists(json, 'label') ? undefined : json['label'],
        'lastModifiedBy': !exists(json, 'lastModifiedBy') ? undefined : json['lastModifiedBy'],
        'lastModifiedDate': !exists(json, 'lastModifiedDate') ? undefined : (new Date(json['lastModifiedDate'])),
    };
}

export function WorkflowBasicModelToJSON(value?: WorkflowBasicModel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'description': value.description,
    };
}

