plugins {
    id("github.mori.java")
    id("github.mori.paper")
    id("github.mori.lombok")
}

dependencies {
    compileOnly(project(":plugin"))
}
