dependencies {
    api(project(":server:libs:hermes:hermes-component:hermes-component-api"))
    api(project(":server:libs:hermes:hermes-task-dispatcher:hermes-task-dispatcher-api"))

    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("org.springframework:spring-core")
    implementation(project(":server:libs:core:commons:commons-util"))
}