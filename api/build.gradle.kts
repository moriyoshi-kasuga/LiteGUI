plugins {
    id("github.mori.java")
    id("github.mori.paper")
    id("maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
