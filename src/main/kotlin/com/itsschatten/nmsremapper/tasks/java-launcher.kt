package com.itsschatten.nmsremapper.tasks

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Nested
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaLauncher
import org.gradle.jvm.toolchain.JavaToolchainService
import org.gradle.kotlin.dsl.getByType
import javax.inject.Inject

/**
 * "Stolen" from https://github.com/PaperMC/paperweight/blob/main/paperweight-lib/src/main/kotlin/io/papermc/paperweight/tasks/java-launcher.kt
 *
 * And the defaultJavaLauncher function is taken from the Utils.kts
 */

private fun JavaLauncherTaskBase.defaultJavaLauncher(project: Project): Provider<JavaLauncher> =
    javaToolchainService.defaultJavaLauncher(project)

fun JavaToolchainService.defaultJavaLauncher(project: Project): Provider<JavaLauncher> {
    return launcherFor(project.extensions.getByType<JavaPluginExtension>().toolchain).orElse(
        launcherFor {
            // If the java plugin isn't applied, or no toolchain value was set
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    )
}

interface JavaLauncherTaskBase {
    @get:Nested
    val launcher: Property<JavaLauncher>

    @get:Inject
    val javaToolchainService: JavaToolchainService
}

abstract class JavaLauncherTask : BaseTask(), JavaLauncherTaskBase {

    override fun init() {
        super.init()

        launcher.convention(defaultJavaLauncher(project))
    }
}