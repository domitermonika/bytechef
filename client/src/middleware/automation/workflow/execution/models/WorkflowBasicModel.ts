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

import { mapValues } from '../runtime';
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
     * The number of workflow connections
     * @type {number}
     * @memberof WorkflowBasicModel
     */
    readonly connectionsCount?: number;
    /**
     * The description of a workflow.
     * @type {string}
     * @memberof WorkflowBasicModel
     */
    description?: string;
    /**
     * The id of a workflow.
     * @type {string}
     * @memberof WorkflowBasicModel
     */
    readonly id?: string;
    /**
     * The number of workflow inputs
     * @type {number}
     * @memberof WorkflowBasicModel
     */
    readonly inputsCount?: number;
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
    /**
     * Does this workflow have a manual trigger or not
     * @type {boolean}
     * @memberof WorkflowBasicModel
     */
    readonly manualTrigger?: boolean;
    /**
     * 
     * @type {Array<string>}
     * @memberof WorkflowBasicModel
     */
    readonly workflowTaskComponentNames?: Array<string>;
    /**
     * 
     * @type {Array<string>}
     * @memberof WorkflowBasicModel
     */
    readonly workflowTriggerComponentNames?: Array<string>;
    /**
     * 
     * @type {number}
     * @memberof WorkflowBasicModel
     */
    version?: number;
}

/**
 * Check if a given object implements the WorkflowBasicModel interface.
 */
export function instanceOfWorkflowBasicModel(value: object): boolean {
    return true;
}

export function WorkflowBasicModelFromJSON(json: any): WorkflowBasicModel {
    return WorkflowBasicModelFromJSONTyped(json, false);
}

export function WorkflowBasicModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): WorkflowBasicModel {
    if (json == null) {
        return json;
    }
    return {
        
        'createdBy': json['createdBy'] == null ? undefined : json['createdBy'],
        'createdDate': json['createdDate'] == null ? undefined : (new Date(json['createdDate'])),
        'connectionsCount': json['connectionsCount'] == null ? undefined : json['connectionsCount'],
        'description': json['description'] == null ? undefined : json['description'],
        'id': json['id'] == null ? undefined : json['id'],
        'inputsCount': json['inputsCount'] == null ? undefined : json['inputsCount'],
        'label': json['label'] == null ? undefined : json['label'],
        'lastModifiedBy': json['lastModifiedBy'] == null ? undefined : json['lastModifiedBy'],
        'lastModifiedDate': json['lastModifiedDate'] == null ? undefined : (new Date(json['lastModifiedDate'])),
        'manualTrigger': json['manualTrigger'] == null ? undefined : json['manualTrigger'],
        'workflowTaskComponentNames': json['workflowTaskComponentNames'] == null ? undefined : json['workflowTaskComponentNames'],
        'workflowTriggerComponentNames': json['workflowTriggerComponentNames'] == null ? undefined : json['workflowTriggerComponentNames'],
        'version': json['__version'] == null ? undefined : json['__version'],
    };
}

export function WorkflowBasicModelToJSON(value?: WorkflowBasicModel | null): any {
    if (value == null) {
        return value;
    }
    return {
        
        'description': value['description'],
        '__version': value['version'],
    };
}

