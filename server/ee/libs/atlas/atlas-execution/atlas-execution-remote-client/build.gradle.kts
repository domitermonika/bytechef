dependencies {
    api(project(":server:libs:atlas:atlas-execution:atlas-execution-api"))

    implementation("org.springframework:spring-context")

    implementation(project(":server:ee:libs:core:commons:commons-webclient"))
}