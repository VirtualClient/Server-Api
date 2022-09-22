plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "gg.virtualclient"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }

}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    implementation(project(":api:common-api"))
    compileOnly("com.velocitypowered:velocity-api:3.1.1-SNAPSHOT")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}