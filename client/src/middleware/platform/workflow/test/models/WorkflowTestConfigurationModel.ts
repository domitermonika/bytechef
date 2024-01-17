/* tslint:disable */
/* eslint-disable */
/**
 * The Platform Workflow Test API
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
import type { WorkflowTestConfigurationConnectionModel } from './WorkflowTestConfigurationConnectionModel';
import {
    WorkflowTestConfigurationConnectionModelFromJSON,
    WorkflowTestConfigurationConnectionModelFromJSONTyped,
    WorkflowTestConfigurationConnectionModelToJSON,
} from './WorkflowTestConfigurationConnectionModel';

/**
 * Contains configuration and connections required for the test execution of a particular workflow.
 * @export
 * @interface WorkflowTestConfigurationModel
 */
export interface WorkflowTestConfigurationModel {
    /**
     * The created by.
     * @type {string}
     * @memberof WorkflowTestConfigurationModel
     */
    readonly createdBy?: string;
    /**
     * The created date.
     * @type {Date}
     * @memberof WorkflowTestConfigurationModel
     */
    readonly createdDate?: Date;
    /**
     * The input parameters used as workflow input values.
     * @type {{ [key: string]: object; }}
     * @memberof WorkflowTestConfigurationModel
     */
    inputs?: { [key: string]: object; };
    /**
     * The connections used by workflow test.
     * @type {Array<WorkflowTestConfigurationConnectionModel>}
     * @memberof WorkflowTestConfigurationModel
     */
    connections?: Array<WorkflowTestConfigurationConnectionModel>;
    /**
     * The id of a workflow test configuration.
     * @type {number}
     * @memberof WorkflowTestConfigurationModel
     */
    readonly id?: number;
    /**
     * The last modified by.
     * @type {string}
     * @memberof WorkflowTestConfigurationModel
     */
    readonly lastModifiedBy?: string;
    /**
     * The last modified date.
     * @type {Date}
     * @memberof WorkflowTestConfigurationModel
     */
    readonly lastModifiedDate?: Date;
    /**
     * The id of a workflow.
     * @type {string}
     * @memberof WorkflowTestConfigurationModel
     */
    workflowId?: string;
    /**
     * 
     * @type {number}
     * @memberof WorkflowTestConfigurationModel
     */
    version?: number;
}

/**
 * Check if a given object implements the WorkflowTestConfigurationModel interface.
 */
export function instanceOfWorkflowTestConfigurationModel(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function WorkflowTestConfigurationModelFromJSON(json: any): WorkflowTestConfigurationModel {
    return WorkflowTestConfigurationModelFromJSONTyped(json, false);
}

export function WorkflowTestConfigurationModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): WorkflowTestConfigurationModel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'createdBy': !exists(json, 'createdBy') ? undefined : json['createdBy'],
        'createdDate': !exists(json, 'createdDate') ? undefined : (new Date(json['createdDate'])),
        'inputs': !exists(json, 'inputs') ? undefined : json['inputs'],
        'connections': !exists(json, 'connections') ? undefined : ((json['connections'] as Array<any>).map(WorkflowTestConfigurationConnectionModelFromJSON)),
        'id': !exists(json, 'id') ? undefined : json['id'],
        'lastModifiedBy': !exists(json, 'lastModifiedBy') ? undefined : json['lastModifiedBy'],
        'lastModifiedDate': !exists(json, 'lastModifiedDate') ? undefined : (new Date(json['lastModifiedDate'])),
        'workflowId': !exists(json, 'workflowId') ? undefined : json['workflowId'],
        'version': !exists(json, '__version') ? undefined : json['__version'],
    };
}

export function WorkflowTestConfigurationModelToJSON(value?: WorkflowTestConfigurationModel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'inputs': value.inputs,
        'connections': value.connections === undefined ? undefined : ((value.connections as Array<any>).map(WorkflowTestConfigurationConnectionModelToJSON)),
        'workflowId': value.workflowId,
        '__version': value.version,
    };
}
