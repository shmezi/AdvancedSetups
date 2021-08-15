plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "me.alexirving.plugin"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public")
    maven("https://repo.mattstudios.me/artifactory/public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    implementation("net.kyori", "adventure-api", "4.8.1")
    implementation("net.kyori", "adventure-platform-bukkit", "4.0.0-SNAPSHOT")
    implementation("dev.triumphteam", "triumph-gui", "3.0.3")
    compileOnly("org.spigotmc", "spigot-api", "1.8.8-R0.1-SNAPSHOT")
    compileOnly("me.clip", "placeholderapi", "2.10.10")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    relocate("dev.triumphteam.gui", "me.alexirving.plugin.gui")
}