group = "com.bytechef.scheduler"
description = ""

springBoot {
    mainClass.set("com.bytechef.scheduler.SchedulerApplication")
}

dependencies {
    implementation(libs.com.github.kagkarlsson.db.scheduler.spring.boot.starter)
    implementation(libs.org.openapitools.jackson.databind.nullable)
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(libs.org.springframework.cloud.spring.cloud.starter.loadbalancer)
    implementation(platform(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES))
    implementation(project(":server:libs:configs:liquibase-config"))
    implementation(project(":server:libs:core:message-broker:message-broker-amqp"))
    implementation(project(":server:libs:core:message-broker:message-broker-kafka"))
    implementation(project(":server:libs:core:message-broker:message-broker-redis"))
    implementation(project(":server:libs:hermes:hermes-scheduler:hermes-scheduler-impl"))

    implementation(project(":server:ee:libs:core:discovery:discovery-redis"))
    implementation(project(":server:ee:libs:hermes:hermes-definition-registry:hermes-definition-registry-remote-client"))
    implementation(project(":server:ee:libs:hermes:hermes-execution:hermes-execution-remote-client"))
    implementation(project(":server:ee:libs:hermes:hermes-scheduler:hermes-scheduler-remote-rest"))

    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.zaxxer:HikariCP")

    testImplementation(project(":server:libs:test:test-int-support"))
}