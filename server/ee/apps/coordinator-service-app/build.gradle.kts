group = "com.bytechef.coordinator"
description = ""

springBoot {
    mainClass.set("com.bytechef.coordinator.CoordinatorApplication")
}

dependencies {
    implementation(libs.org.openapitools.jackson.databind.nullable)
    implementation("org.springframework:spring-webflux")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(libs.org.springframework.cloud.spring.cloud.starter.loadbalancer)
    implementation("org.springframework.kafka:spring-kafka")
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation(project(":server:libs:atlas:atlas-coordinator:atlas-coordinator-config"))
    implementation(project(":server:libs:core:encryption:encryption-filesystem"))
    implementation(project(":server:libs:core:error:error-impl"))
    implementation(project(":server:libs:core:event:event-impl"))
    implementation(project(":server:libs:core:event:event-listener:event-listener-impl"))
    implementation(project(":server:libs:core:commons:commons-data"))
    implementation(project(":server:libs:core:message-broker:message-broker-amqp"))
    implementation(project(":server:libs:core:message-broker:message-broker-kafka"))
    implementation(project(":server:libs:core:message-broker:message-broker-redis"))
    implementation(project(":server:libs:core:rest:rest-impl"))
    implementation(project(":server:libs:helios:helios-coordinator"))
    implementation(project(":server:libs:hermes:hermes-coordinator:hermes-coordinator-impl"))
    implementation(project(":server:libs:hermes:hermes-definition-registry:hermes-definition-registry-service"))

    implementation(project(":server:libs:modules:task-dispatchers:branch"))
    implementation(project(":server:libs:modules:task-dispatchers:condition"))
    implementation(project(":server:libs:modules:task-dispatchers:each"))
    implementation(project(":server:libs:modules:task-dispatchers:forkjoin"))
    implementation(project(":server:libs:modules:task-dispatchers:loop"))
    implementation(project(":server:libs:modules:task-dispatchers:map"))
    implementation(project(":server:libs:modules:task-dispatchers:parallel"))
    implementation(project(":server:libs:modules:task-dispatchers:sequence"))
    implementation(project(":server:libs:modules:task-dispatchers:subflow"))

    implementation(project(":server:ee:libs:atlas:atlas-execution:atlas-execution-remote-client"))
    implementation(project(":server:ee:libs:core:discovery:discovery-redis"))
    implementation(project(":server:ee:libs:helios:helios-configuration:helios-configuration-remote-client"))
    implementation(project(":server:ee:libs:hermes:hermes-configuration:hermes-configuration-remote-client"))
    implementation(project(":server:ee:libs:hermes:hermes-definition-registry:hermes-definition-registry-remote-rest"))
    implementation(project(":server:ee:libs:hermes:hermes-execution:hermes-execution-remote-client"))

    testImplementation(project(":server:libs:test:test-int-support"))
}