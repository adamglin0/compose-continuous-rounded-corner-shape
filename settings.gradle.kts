pluginManagement {
    repositories {
        maven("https://packages.jetbrains.team/maven/p/firework/dev")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        maven("https://packages.jetbrains.team/maven/p/firework/dev")
        google()
        mavenCentral()
    }
}

rootProject.name = "compose-super-rounded-corner-shape"
include(":super-rounded-corner-shape")
// apps
include(":sample:compose-app")