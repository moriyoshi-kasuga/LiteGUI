plugins {
    id("github.mori.java")
}

val lombok = "org.projectlombok:lombok:1.18.38"

dependencies {
  compileOnly(lombok)
  annotationProcessor(lombok)

  testCompileOnly(lombok)
  testAnnotationProcessor(lombok)
}
