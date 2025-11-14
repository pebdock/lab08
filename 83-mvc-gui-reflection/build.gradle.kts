plugins {
    application
    java
    id("org.danilopianini.gradle-java-qa") version "1.153.0"
}

dependencies {
    //Suppress Spotbugs warnings
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("it.unibo.mvc.LaunchApp")
}

tasks.javadoc {
    isFailOnError = false
}
