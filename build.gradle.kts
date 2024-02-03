import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    kotlin("jvm") version "1.9.0"
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
}

group = "mc.tsukimiya"
version = "1.0-SNAPSHOT"

val mcVersion = "1.20.1"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:${mcVersion}-R0.1-SNAPSHOT")
    library(kotlin("stdlib"))
    library("org.jetbrains.exposed:exposed-core:0.41.1")
    library("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    library("org.jetbrains.exposed:exposed-dao:0.41.1")
    library("org.xerial:sqlite-jdbc:3.41.2.2")
    library("com.mysql:mysql-connector-j:8.2.0")
}

bukkit {
    name = "NameLogger"
    version = getVersion().toString()
    description = "プレイヤー名ログ取得"
    author = "deceitya"
    main = "mc.tsukimiya.NameLogger"
    apiVersion = mcVersion.substring(0, mcVersion.lastIndexOf("."))
    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/tsukimiyaproject/NameLogger")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}