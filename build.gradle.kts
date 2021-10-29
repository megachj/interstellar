import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    val kotlinVersion = "1.5.20"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

allprojects {
    repositories {
        mavenCentral()
    }

    ext {
        set("kotestVersion", "4.2.6")
        set("mockkVersion", "1.11.0")
        set("kotlinLoggingVersion", "2.0.8")
        set("coroutinesSlf4jVersion", "1.5.1")
    }
}

// 실행가능한 application 이 아닌, 라이브러리용 모듈
val libraryModules = listOf("")

configure(subprojects.filter { it.name !in libraryModules }) {

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")

    group = "megachj"
    version = "0.0.1-SNAPSHOT"

    val kotestVersion: String by ext
    val mockkVersion: String by ext
    val kotlinLoggingVersion: String by ext
    val coroutinesSlf4jVersion: String by ext

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.kotest:kotest-runner-junit5-jvm:${kotestVersion}")
        testImplementation("io.mockk:mockk:${mockkVersion}")

        implementation("io.github.microutils:kotlin-logging-jvm:${kotlinLoggingVersion}")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:${coroutinesSlf4jVersion}")
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

configure(listOf(rootProject) + subprojects.filter { it.parent?.name in libraryModules }) {
    tasks.bootJar { enabled = false }
    tasks.jar { enabled = true }
}
