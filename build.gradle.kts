plugins {
    id("dev.prism")
}

group = "com.leclowndu93150"
version = "1.1.0"

prism {
    metadata {
        modId = "sinkfaster"
        name = "Sink Faster"
        description = "Sink faster in water, with configurable multiplier."
        license = "MIT"
        author("Leclowndu93150")
    }

    sharedCommon {
        dependencies {
            compileOnly("com.google.code.gson:gson:2.10.1")
        }
    }

    version("1.20.1") {
        minecraftVersions("1.20.1")

        fabric {
            loaderVersion = "0.19.2"
            fabricApi("0.92.8+1.20.1")
        }
        forge {
            loaderVersion = "47.4.20"
            loaderVersionRange = "[47,)"
        }
    }

    version("1.21.1") {
        minecraftVersions("1.21.1")

        fabric {
            loaderVersion = "0.19.2"
            fabricApi("0.116.11+1.21.1")
        }
        neoforge {
            loaderVersion = "21.1.226"
            loaderVersionRange = "[21.1,)"
        }
    }

    version("26.1.2") {
        minecraftVersions("26.1.2")

        fabric {
            loaderVersion = "0.19.2"
            fabricApi("0.146.0+26.2")
        }
        neoforge {
            loaderVersion = "26.1.2.11-beta"
            loaderVersionRange = "[26.1,)"
        }
    }

    publishing {
        changelogFile = "CHANGELOG.md"
        type = dev.prism.gradle.dsl.ReleaseType.STABLE

        curseforge {
            accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
            projectId = "1515210"
        }
    }
}
