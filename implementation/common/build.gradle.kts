plugins {
    id("java")
}

group = "gg.virtualclient"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    compileOnly("io.netty:netty-all:4.1.81.Final")
    compileOnly("com.google.code.gson:gson:2.9.1")
    implementation(project(":api:common-api"))
    compileOnly("net.kyori:adventure-api:4.11.0")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}