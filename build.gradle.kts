group = "gg.virtualclient"
version = "1.0-SNAPSHOT"


subprojects {
    apply {
        plugin("java")
    }

    configure<JavaPluginExtension>{
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }
}