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
import type { ProjectInstanceWorkflowConnectionModel } from './ProjectInstanceWorkflowConnectionModel';
import {
    ProjectInstanceWorkflowConnectionModelFromJSON,
    ProjectInstanceWorkflowConnectionModelFromJSONTyped,
    ProjectInstanceWorkflowConnectionModelToJSON,
} from './ProjectInstanceWorkflowConnectionModel';

/**
 * Contains configuration and connections required for the execution of a particular project workflow.
 * @export
 * @interface ProjectInstanceWorkflowModel
 */
export interface ProjectInstanceWorkflowModel {
    /**
     * The input parameters of an project instance used as workflow input values.
     * @type {{ [key: string]: object; }}
     * @memberof ProjectInstanceWorkflowModel
     */
    inputs?: { [key: string]: object; };
    /**
     * The connections used by a project instance.
     * @type {Array<ProjectInstanceWorkflowConnectionModel>}
     * @memberof ProjectInstanceWorkflowModel
     */
    connections?: Array<ProjectInstanceWorkflowConnectionModel>;
    /**
     * If a workflow is enabled or not in the project instance workflow.
     * @type {boolean}
     * @memberof ProjectInstanceWorkflowModel
     */
    enabled?: boolean;
    /**
     * The id of a project instance.
     * @type {number}
     * @memberof ProjectInstanceWorkflowModel
     */
    readonly id?: number;
    /**
     * The last execution date of a project instance.
     * @type {Date}
     * @memberof ProjectInstanceWorkflowModel
     */
    lastExecutionDate?: Date;
    /**
     * The id of a project instance.
     * @type {number}
     * @memberof ProjectInstanceWorkflowModel
     */
    projectInstanceId?: number;
    /**
     * The id of a workflow.
     * @type {string}
     * @memberof ProjectInstanceWorkflowModel
     */
    workflowId?: string;
    /**
     * 
     * @type {number}
     * @memberof ProjectInstanceWorkflowModel
     */
    version?: number;
}

/**
 * Check if a given object implements the ProjectInstanceWorkflowModel interface.
 */
export function instanceOfProjectInstanceWorkflowModel(value: object): boolean {
    let isInstance = true;

    return isInstance;
}

export function ProjectInstanceWorkflowModelFromJSON(json: any): ProjectInstanceWorkflowModel {
    return ProjectInstanceWorkflowModelFromJSONTyped(json, false);
}

export function ProjectInstanceWorkflowModelFromJSONTyped(json: any, ignoreDiscriminator: boolean): ProjectInstanceWorkflowModel {
    if ((json === undefined) || (json === null)) {
        return json;
    }
    return {
        
        'inputs': !exists(json, 'inputs') ? undefined : json['inputs'],
        'connections': !exists(json, 'connections') ? undefined : ((json['connections'] as Array<any>).map(ProjectInstanceWorkflowConnectionModelFromJSON)),
        'enabled': !exists(json, 'enabled') ? undefined : json['enabled'],
        'id': !exists(json, 'id') ? undefined : json['id'],
        'lastExecutionDate': !exists(json, 'lastExecutionDate') ? undefined : (new Date(json['lastExecutionDate'])),
        'projectInstanceId': !exists(json, 'projectInstanceId') ? undefined : json['projectInstanceId'],
        'workflowId': !exists(json, 'workflowId') ? undefined : json['workflowId'],
        'version': !exists(json, '__version') ? undefined : json['__version'],
    };
}

export function ProjectInstanceWorkflowModelToJSON(value?: ProjectInstanceWorkflowModel | null): any {
    if (value === undefined) {
        return undefined;
    }
    if (value === null) {
        return null;
    }
    return {
        
        'inputs': value.inputs,
        'connections': value.connections === undefined ? undefined : ((value.connections as Array<any>).map(ProjectInstanceWorkflowConnectionModelToJSON)),
        'enabled': value.enabled,
        'lastExecutionDate': value.lastExecutionDate === undefined ? undefined : (value.lastExecutionDate.toISOString()),
        'projectInstanceId': value.projectInstanceId,
        'workflowId': value.workflowId,
        '__version': value.version,
    };
}

