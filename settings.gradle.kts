rootProject.name = "otuskotlin-test-project"
pluginManagement {
    val kotlinVersion: String by settings
    val openapiVersion: String by settings
    val cwpGeneratorVersioin: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion apply false

        id("org.openapi.generator") version openapiVersion apply false
        id("com.crowdproj.generator") version cwpGeneratorVersioin apply false
    }
}
include("m1l1-first-app")
include("microblog-api-v1-jackson")
include("microblog-api-v2-kmp")
include("microblog-common")
include("microblog-mappers-v1")
include("microblog-mappers-v2")