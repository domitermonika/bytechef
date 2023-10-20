group = "com.bytechef.configuration"
description = ""

springBoot {
    mainClass.set("com.bytechef.configuration.ConfigurationApplication")
}

dependencies {
    implementation(libs.org.openapitools.jackson.databind.nullable)
    implementation(libs.org.springdoc.springdoc.openapi.starter.webmvc.ui)
    implementation("org.springframework:spring-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.retry:spring-retry")
    implementation(libs.org.springframework.cloud.spring.cloud.starter.loadbalancer)
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation(project(":server:libs:atlas:atlas-configuration:atlas-configuration-repository:atlas-configuration-repository-jdbc"))
    implementation(project(":server:libs:atlas:atlas-configuration:atlas-configuration-repository:atlas-configuration-repository-jdbc-util"))
    implementation(project(":server:libs:atlas:atlas-configuration:atlas-configuration-repository:atlas-configuration-repository-git"))
    implementation(project(":server:libs:atlas:atlas-configuration:atlas-configuration-repository:atlas-configuration-repository-resource"))
    implementation(project(":server:libs:atlas:atlas-configuration:atlas-configuration-service"))
    implementation(project(":server:libs:configs:account-config"))
    implementation(project(":server:libs:configs:liquibase-config"))
    implementation(project(":server:libs:core:category:category-service"))
    implementation(project(":server:libs:core:commons:commons-data"))
    implementation(project(":server:libs:core:rest:rest-impl"))
    implementation(project(":server:libs:core:tag:tag-service"))
    implementation(project(":server:libs:helios:helios-configuration:helios-configuration-rest:helios-configuration-rest-impl"))
    implementation(project(":server:libs:helios:helios-configuration:helios-configuration-service"))
    implementation(project(":server:libs:hermes:hermes-configuration:hermes-configuration-rest"))
    implementation(project(":server:libs:hermes:hermes-configuration:hermes-configuration-service"))

    implementation(project(":server:ee:libs:atlas:atlas-execution:atlas-execution-remote-client"))
    implementation(project(":server:ee:libs:core:discovery:discovery-redis"))
    implementation(project(":server:ee:libs:dione:dione-configuration:dione-configuration-rest"))
    implementation(project(":server:ee:libs:dione:dione-configuration:dione-configuration-service"))
    implementation(project(":server:ee:libs:helios:helios-configuration:helios-configuration-remote-rest"))
    implementation(project(":server:ee:libs:hermes:hermes-configuration:hermes-configuration-remote-rest"))
    implementation(project(":server:ee:libs:hermes:hermes-connection:hermes-connection-remote-client"))
    implementation(project(":server:ee:libs:hermes:hermes-definition-registry:hermes-definition-registry-remote-client"))
    implementation(project(":server:ee:libs:hermes:hermes-execution:hermes-execution-remote-client"))

    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.zaxxer:HikariCP")

    testImplementation(project(":server:libs:test:test-int-support"))
}