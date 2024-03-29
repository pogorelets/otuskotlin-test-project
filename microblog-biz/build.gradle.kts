plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm {}
    linuxX64 {}
    macosX64 {}
    macosArm64 {}

    sourceSets {
        val coroutinesVersion: String by project
        all { languageSettings.optIn("kotlin.RequiresOptIn") }
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(project(":microblog-common"))
                implementation(project(":microblog-stubs"))

                val kotlinCorVersion: String by project
                implementation("com.crowdproj:kotlin-cor:$kotlinCorVersion")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                api("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
            }
        }
        jvmMain {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }
        jvmTest {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}