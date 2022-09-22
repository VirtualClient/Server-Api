rootProject.name = "Server-Api"
pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.fabricmc.net/")
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

include("api:common-api")
include("api:minestom-api")
include("api:spigot-api")
include("api:fabric-api")
include("api:velocity-api")

include("implementation:common")
include("implementation:minestom")
include("implementation:spigot")
include("implementation:fabric")
include("implementation:velocity")
