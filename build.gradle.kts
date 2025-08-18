import xyz.jpenilla.runtask.RunExtension
import org.gradle.internal.os.OperatingSystem

plugins {
  id("base")
  id("github.mori.java") apply false
  id("github.mori.paper") apply false
  id("xyz.jpenilla.run-paper") version "3.0.0-beta.1"
  id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.3.0" apply false
}

group = "github.mori"
version = "0.1.0-SNAPSHOT"

tasks.runServer {
  minecraftVersion("1.21.8")
  jvmArgs("-Dpaper.disablePluginRemapping=true", "-Dfile.encoding=UTF-8", "-Dcom.mojang.eula.agree=true")
}
