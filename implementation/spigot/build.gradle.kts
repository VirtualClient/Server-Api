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
    maven { url = uri("https://repo.dmulloy2.net/repository/public/") }

}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    implementation(project(":api:common-api"))
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    implementation(project(":api:spigot-api"))

    implementation(project(":implementation:common"))
    compileOnly("io.netty:netty-all:4.1.81.Final")
    compileOnly("com.comphenix.protocol:ProtocolLib:4.8.0");
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}