package com.itsschatten.nmsremapper

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.attributes.Bundling
import org.gradle.api.attributes.Category
import org.gradle.api.attributes.LibraryElements
import org.gradle.api.attributes.Usage
import org.gradle.api.tasks.TaskContainer
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.registering

class NMSRemapPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // The remap task.
        val remap by project.tasks.registering<MojangSpigotRemapTask> {
        }

        // remap configuration, used to ensure the proper jar.
        project.configurations.register("remap") {
            isCanBeResolved = true
            isCanBeResolved = false
            attributes {
                attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage.JAVA_RUNTIME))
                attribute(Category.CATEGORY_ATTRIBUTE, project.objects.named(Category.LIBRARY))
                attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, project.objects.named(LibraryElements.JAR))
                attribute(Bundling.BUNDLING_ATTRIBUTE, project.objects.named(Bundling.EXTERNAL))
            }
            outgoing.artifact(remap)
        }
    }

    // Taken from Paperweight-lib utils.kt
    inline fun <reified T : Task> TaskContainer.registering(noinline configuration: T.() -> Unit) =
        registering(T::class, configuration)

    inline fun <reified T : Task> TaskContainer.registering() = registering(T::class)
}