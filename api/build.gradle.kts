subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "java-library")

    configure<JavaPluginExtension>{
        withSourcesJar()
    }

    configure<PublishingExtension>{
        publications {
            create<MavenPublication>("maven") {
                groupId = "${project.group}"
                artifactId = project.name
                version = "${project.version}"

                from(components["java"])
            }
        }
        repositories {
            maven {
                name = "virtualclientRepository"
                credentials(PasswordCredentials::class)
                url = uri("https://repo.virtualclient.gg/artifactory/virtualclient-public/")
            }
        }
    }



}