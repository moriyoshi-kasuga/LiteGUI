import xyz.jpenilla.runtask.RunExtension
import org.gradle.internal.os.OperatingSystem

plugins {
  id("github.mori.java") apply false
  id("github.mori.paper") apply false
  id("github.mori.lombok") apply false
  id("xyz.jpenilla.run-paper") version "3.0.0-beta.1"
  id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.3.0" apply false
}

group = "github.mori"
version = "0.1.0-SNAPSHOT"

interface FsInjected {
  @get:Inject val fs: FileSystemOperations
}

tasks.register("buildAndCopy") {
  dependsOn(project(":plugin").tasks.getByName("build"))
  val injected = project.objects.newInstance<FsInjected>()
  doLast {
    injected.fs.delete { delete("run/plugins/LiteGUI.jar") }
    injected.fs.copy {
      from(project(":plugin").layout.buildDirectory.file("libs/LiteGUI.jar"))
      into("run/plugins")
    }
  }
}

tasks.runServer {
  dependsOn("buildAndCopy")
  minecraftVersion("1.21.8")
  jvmArgs("-Dpaper.disablePluginRemapping=true", "-Dfile.encoding=UTF-8", "-Dcom.mojang.eula.agree=true")
}
