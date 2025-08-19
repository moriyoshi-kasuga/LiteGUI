import xyz.jpenilla.runtask.RunExtension
import org.gradle.internal.os.OperatingSystem

plugins {
  id("github.mori.java") apply false
  id("github.mori.paper") apply false
  id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.3.0" apply false

  id("base")
  id("xyz.jpenilla.run-paper") version "3.0.0-beta.1"
}

allprojects {
  group = "com.github.mori"
  version = "0.2.0"
}

tasks.runServer {
  minecraftVersion("1.21.8")
  jvmArgs("-Dpaper.disablePluginRemapping=true", "-Dfile.encoding=UTF-8", "-Dcom.mojang.eula.agree=true")
}
