import xyz.jpenilla.runtask.RunExtension
import org.gradle.internal.os.OperatingSystem
import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
  id("github.mori.java")
  id("github.mori.paper")
  id("github.mori.lombok")
  id("xyz.jpenilla.run-paper") version "3.0.0-beta.1"
  id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.3.0"
}

group = "github.mori"
version = "0.1.0-SNAPSHOT"

interface FsInjected {
  @get:Inject val fs: FileSystemOperations
}

tasks.jar {
  archiveFileName.set("LiteGUI.jar")
}

tasks.register("buildAndCopy") {
  dependsOn("build")
  val injected = project.objects.newInstance<FsInjected>()
  doLast {
    injected.fs.delete { delete("run/plugins/LiteGUI.jar") }
    injected.fs.copy {
      from("build/libs/LiteGUI.jar")
      into("run/plugins")
    }
  }
}

tasks.runServer {
  dependsOn("buildAndCopy")
  minecraftVersion("1.21.8")
  jvmArgs("-Dpaper.disablePluginRemapping=true", "-Dfile.encoding=UTF-8", "-Dcom.mojang.eula.agree=true")
}

bukkitPluginYaml {
  main = "github.mori.litegui.LiteGUIPlugin"
  name = "LiteGUI"
  description = "A simple GUI library for Minecraft plugins"
  authors = listOf("moriyoshi-kasuga")
  apiVersion = "1.21"
  website = "https://github.com/moriyoshi-kasuga/LiteGUI"
  load = BukkitPluginYaml.PluginLoadOrder.STARTUP
}
