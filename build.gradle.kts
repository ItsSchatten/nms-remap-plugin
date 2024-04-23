plugins {
    idea
    id("org.gradle.kotlin.kotlin-dsl") version ("4.2.1")

    id("java-gradle-plugin")
    // id("com.gradle.plugin-publish") version "1.0.0-rc-2"
    id("maven-publish")
}

version = "1.0.0"
group = "com.itsschatten"

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
    target {
        compilations.configureEach {
            kotlinOptions {
                jvmTarget = "17"
                freeCompilerArgs = listOf("-Xjvm-default=all", "-Xjdk-release=17")
            }
        }
    }
}

tasks.jar {
    manifest {
        attributes(
            "Implementation-Version" to project.version
        )
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // SpecialSource
    implementation("net.md-5:SpecialSource:1.11.4")
}

gradlePlugin {
    plugins {
        create("nmsRemap") {
            id = "com.itsschatten.nmsremapper"
            implementationClass = "com.itsschatten.nmsremapper.NMSRemapPlugin"
            displayName =
                "Remapping plugin for working with Spigot server internals, if working with Paper use their userdev plugin."
        }
    }
}

/*
pluginBundle {
    website = "https://github.com/tagavari/nms-remap"
    vcsUrl = "https://github.com/tagavari/nms-remap"

    description = "Use SpecialSource to remap Spigot plugin outputs"
    tags = listOf("minecraft", "specialsource", "nms", "spigot", "mojang")
}*/
