plugins {
    id("java")
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")

}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    compileOnly("com.github.Minestom:Minestom:18c46481f4")
    api(project(":api:common-api"))

}

configure<JavaPluginExtension>{
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}