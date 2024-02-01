import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.sonarqube") version "4.4.1.3373"
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

sonar {
    properties {
        property("sonar.projectKey", "fiap-postech-tech-challenge_tech-challenge")
        property("sonar.organization", "fiap-postech-tech-challenge")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-test-autoconfigure")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}