plugins {
    id("java")
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
    implementation(project(":api:minestorm-api"))

    implementation(project(":implementation:common"))

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}