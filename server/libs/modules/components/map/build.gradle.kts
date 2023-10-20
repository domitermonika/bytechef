version="1.0"

dependencies {
    implementation("org.slf4j:slf4j-api")
    implementation("org.springframework:spring-context")
    implementation(project(":server:libs:atlas:atlas-configuration:atlas-configuration-api"))
    implementation(project(":server:libs:atlas:atlas-execution:atlas-execution-service"))
    implementation(project(":server:libs:atlas:atlas-execution:atlas-execution-repository:atlas-execution-repository-memory"))
    implementation(project(":server:libs:atlas:atlas-worker:atlas-worker-impl"))
    implementation(project(":server:libs:core:message-broker:message-broker-sync"))
    implementation(project(":server:libs:hermes:hermes-component:hermes-component-api"))
    implementation(project(":server:libs:modules:task-dispatchers:map"))

    testImplementation(project(":server:libs:core:commons:commons-util"))
}