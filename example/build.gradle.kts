import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
    id("github.mori.java")
    id("github.mori.paper")
    id("xyz.jpenilla.resource-factory-bukkit-convention")
}

dependencies {
    compileOnly(project(":api"))
}

tasks.jar {
  archiveFileName.set("ExamplePlugin.jar")
}

bukkitPluginYaml {
  main = "com.example.litegui.ExamplePlugin"
  name = "ExamplePlugin"
  description = "An example plugin using LiteGUI"
  authors = listOf("moriyoshi-kasuga")
  apiVersion = "1.21"
  website = "https://github.com/moriyoshi-kasuga/LiteGUI"
  load = BukkitPluginYaml.PluginLoadOrder.STARTUP
  depend = listOf("LiteGUI")
  commands {
    register("example") {
      description = "An example command"
      usage = "/example"
    }
  }
}

interface FsInjected {
  @get:Inject val fs: FileSystemOperations
}

tasks.register("buildAndCopy") {
  dependsOn("build")
  val injected = project.objects.newInstance<FsInjected>()
  doLast {
    injected.fs.delete { delete("run/plugins/ExamplePlugin.jar") }
    injected.fs.copy {
      from("build/libs/ExamplePlugin.jar")
      into("../run/plugins")
    }
  }
}
