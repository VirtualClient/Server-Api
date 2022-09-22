


subprojects {
    group = "gg.virtualclient.server-api"
    version = "1.0-SNAPSHOT"
    apply {
        plugin("java")
    }

    configure<JavaPluginExtension>{
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }
}