import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
    id("github.mori.java")
    id("github.mori.paper")
    id("xyz.jpenilla.resource-factory-bukkit-convention")
}

dependencies {
    implementation(project(":api"))
}

tasks.jar {
  archiveFileName.set("LiteGUI.jar")
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
