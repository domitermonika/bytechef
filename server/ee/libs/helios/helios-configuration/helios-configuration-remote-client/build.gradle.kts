dependencies {
    api(project(":server:libs:helios:helios-configuration:helios-configuration-api"))

    implementation("org.springframework:spring-context")

    implementation(project(":server:ee:libs:core:commons:commons-webclient"))
}