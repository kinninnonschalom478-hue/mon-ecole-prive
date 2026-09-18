pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MonEcole"

include(":parent")
include(":teacher")
include(":admin")

project(":parent").projectDir = file("apps/parent")
project(":teacher").projectDir = file("apps/teacher")
project(":admin").projectDir = file("apps/admin")
