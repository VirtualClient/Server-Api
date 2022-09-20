plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "gg.virtualclient"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")

}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    compileOnly("com.github.Minestom:Minestom:18c46481f4")
    implementation(project(":api:common-api"))
    implementation(project(":api:minestom-api"))

    compileOnly("net.kyori:adventure-text-serializer-gson:4.11.0")

    implementation(project(":implementation:common"))

}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}