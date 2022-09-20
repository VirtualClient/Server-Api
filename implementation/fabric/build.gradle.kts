import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.fabricmc.loom.task.RemapJarTask

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("fabric-loom") version "1.0-SNAPSHOT"

}

group = "gg.virtualclient"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }

}

//sourceCompatibility = JavaVersion.VERSION_8
//targetCompatibility = JavaVersion.VERSION_8

configurations.implementation.get().extendsFrom(project.configurations.shadow.get())


dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:1.19.2")
    mappings("net.fabricmc:yarn:1.19.2+build.8:v2")
    modImplementation("net.fabricmc:fabric-loader:0.14.9")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.60.0+1.19.2")
    shadow(project(":api:common-api"))
    shadow(project(":api:fabric-api"))

    shadow(project(":implementation:common"))
    compileOnly("net.md-5:bungeecord-chat:1.16-R0.4")
    compileOnly("net.kyori:adventure-api:4.11.0")

}



val shadowJar by tasks.getting(ShadowJar::class) {
    configurations = listOf(project.configurations.shadow.get())
}

tasks.getByName<RemapJarTask>("remapJar") {
    inputFile.set(shadowJar.archiveFile)
}

//tasks.withType(JavaCompile::class).configureEach {
//    it.options.release = 8
//}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}