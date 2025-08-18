plugins {
    id("github.mori.java")
    id("github.mori.paper")
    id("maven-publish")
}

group = "com.github.mori"
version = "0.1.1"

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
