plugins {
    kotlin("jvm") apply false
    application
}

group = "ru.otus.otuskotlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenCentral()
    }
}