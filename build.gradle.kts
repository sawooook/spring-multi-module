import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("org.springframework.boot") version "2.7.3" apply false
    id("io.spring.dependency-management") version "1.0.13.RELEASE" apply false
    kotlin("plugin.spring") version "1.6.21" apply false
    kotlin("plugin.jpa") version "1.6.21" apply false
}

java.sourceCompatibility = JavaVersion.VERSION_11

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}


subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        runtimeOnly("com.h2database:h2")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.getByName("bootJar") {
        enabled  = false
    }

    tasks.getByName("jar") {
        enabled  = false
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
