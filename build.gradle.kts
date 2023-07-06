plugins {
    id("java")
    id("xyz.jpenilla.run-paper") version "2.1.0"
}

group = "com.edadimperial"
version = "0.1-ALPHA"

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20.1-R0.1-SNAPSHOT")
}

tasks {
    runServer {
        minecraftVersion("1.19.4")
    }
}
