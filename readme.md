# NMS Remap Gradle Plugin

**This is a modified fork of [Tagavari's NMS-Remap Gradle Plugin](https://github.com/tagavari/nms-remap) modified with
portions of [Paper's paperweight](https://github.com/PaperMC/paperweight).**

This plugin does not work with Paper files, please use paperweight.

---

A Gradle plugin for remapping artifacts using [SpecialSource](https://github.com/md-5/SpecialSource),
with shortcuts for Spigot NMS plugin developers.

Inspired by the work done by
[SpecialSourceMP](https://github.com/agaricusb/SpecialSourceMP) and
[mojang-spigot-remapper](https://github.com/patrick-choe/mojang-spigot-remapper).

### About the plugin

Mojang now releases their obfuscation maps for Minecraft,
which makes it possible to directly invoke Minecraft's internal code
by using readable member names.

However, these references must be mapped back to their obfuscated
counterparts in order to run on Spigot servers.

For more details,
see [the NMS section on the Spigot 1.17 release post](https://www.spigotmc.org/threads/spigot-bungeecord-1-17-1-17-1.510208/#post-4184317).

An official Maven plugin is available to perform this remapping automatically when assembling a jar file.
This plugin aims to bring the same functionality to Gradle users, while also being easy to use.

### Getting started

Run BuildTools with the `--remapped` option
in order to install Mojang and Spigot obfuscation maps to your local repository.

Then, add the plugin to your project:

#### Kotlin

```kotlin
plugins {
    id("com.itsschatten.nmsremapper") version "1.0.0"
}
```

#### Groovy

```groovy
plugins {
    id "com.itsschatten.nmsremapper" version "1.0.0"
}
```

### How To

A `remap` task will be added to your project.
By default, it will try to detect which version of Minecraft you're compiling for,
and remap the output of the `jar` task automatically.

```shell
./gradlew remap
```

The `remap` task has these configuration options:

```kotlin
tasks {
    remap {
        //Overrides the Minecraft version to remap against.
        //You may use this option if your version can't automatically be detected.
        //Must match a valid Spigot dependency version.
        version.set("1.18.2-R0.1-SNAPSHOT")

        //Overrides the default input file
        inputFile.set(uberJar.archiveFile)

        //The classifier to add to the end of the output file
        //(if archiveName is not specified)
        archiveClassifier.set("remapped")

        //The name to use for the output file
        //(if outputFile is not specified)
        archiveName.set("plugin-remapped.jar")

        //The archive file to write to
        outputFile.set(File(buildDir, "plugin-remapped.jar"))
    }
}
```

_Removed advanced method in this fork._

