plugins {
    id("java")
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

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:1.16.5")
    mappings("net.fabricmc:yarn:1.16.5+build.8:v2")
    modCompileOnly("net.fabricmc:fabric-loader:0.14.9")

    implementation(project(":api:common-api"))
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