plugins {
    id("java")
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

tasks.jar {
    destinationDirectory.set(file("server/plugins"))
}