plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":microblog-api-v1-jackson"))
    implementation(project(":microblog-common"))

    testImplementation(kotlin("test-junit"))
}