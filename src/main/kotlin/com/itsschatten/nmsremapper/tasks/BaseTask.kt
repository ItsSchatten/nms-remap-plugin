package com.itsschatten.nmsremapper.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.ArchiveOperations
import org.gradle.api.file.FileSystemOperations
import org.gradle.api.file.ProjectLayout
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

/**
 * "Stolen" from paperweight.
 * https://github.com/PaperMC/paperweight/blob/main/paperweight-lib/src/main/kotlin/io/papermc/paperweight/tasks/BaseTask.kt
 */
abstract class BaseTask : DefaultTask() {

    @get:Inject
    abstract val objects: ObjectFactory

    @get:Inject
    abstract val layout: ProjectLayout

    @get:Inject
    abstract val fs: FileSystemOperations

    @get:Inject
    abstract val archives: ArchiveOperations

    open fun init() {}

    init {
        this.init()
    }
}