dependencies {
    api(project(":server:libs:core:event:event-api"))
    api(project(":server:libs:hermes:hermes-connection:hermes-connection-api"))
    api(project(":server:libs:hermes:hermes-data-storage:hermes-data-storage-api"))
    api(project(":server:libs:hermes:hermes-definition-registry:hermes-definition-registry-api"))
    api(project(":server:libs:hermes:hermes-file-storage:hermes-file-storage-api"))

    implementation(project(":server:libs:atlas:atlas-execution:atlas-execution-api"))
    implementation(project(":server:libs:core:commons:commons-util"))
    implementation(project(":server:libs:hermes:hermes-component:hermes-component-context:hermes-component-context-api"))
}