dependencies {
    api(project(":server:libs:atlas:atlas-worker:atlas-worker-api"))

    implementation("org.slf4j:slf4j-api")
    implementation("org.springframework:spring-context")
    implementation(project(":server:libs:atlas:atlas-configuration:atlas-configuration-api"))
    implementation(project(":server:libs:core:message-broker:message-broker-sync"))
    implementation(project(":server:libs:core:commons:commons-util"))

    testImplementation("org.springframework:spring-jdbc")
    testImplementation("org.springframework.security:spring-security-core")
}