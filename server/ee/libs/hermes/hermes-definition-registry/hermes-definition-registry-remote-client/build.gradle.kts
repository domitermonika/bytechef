dependencies {
    api(project(":server:libs:hermes:hermes-definition-registry:hermes-definition-registry-api"))

    api(project(":server:ee:libs:core:commons:commons-webclient"))

    implementation("io.projectreactor:reactor-core")
    implementation(libs.org.springframework.cloud.spring.cloud.commons)
    implementation(project(":server:libs:core:commons:commons-util"))

    implementation(project(":server:ee:libs:core:commons:commons-discovery"))
}