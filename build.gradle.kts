import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"

    val kotlinVersion = "1.5.20"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
}

allprojects {
    group = "sunset"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "kotlin")

    ext {
        set("jacksonVersion", "2.13.0")
        set("jetbrainsKotlinLibraryVersion", "1.6.0")
        set("kotestVersion", "4.2.6")
        set("mockkVersion", "1.11.0")
        set("kotlinLoggingVersion", "2.0.8")
        set("coroutinesSlf4jVersion", "1.5.1")
    }

    // variables
    val jacksonVersion: String by ext
    val jetbrainsKotlinLibraryVersion: String by ext
    val kotestVersion: String by ext
    val mockkVersion: String by ext
    val kotlinLoggingVersion: String by ext
    val coroutinesSlf4jVersion: String by ext

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
        implementation("org.jetbrains.kotlin:kotlin-reflect:$jetbrainsKotlinLibraryVersion")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$jetbrainsKotlinLibraryVersion")

        testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
        testImplementation("io.mockk:mockk:$mockkVersion")

        implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:$coroutinesSlf4jVersion")
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

// 하위 모듈 설정
val springKotlinModules = listOf("")
configure(subprojects.filter { it.name in springKotlinModules }) {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    }
}

val libraryGroups = listOf("")
configure(listOf(rootProject) + subprojects.filter { it.parent?.name in libraryGroups }) {
    tasks.bootJar { enabled = false }
    tasks.jar { enabled = true }
}
