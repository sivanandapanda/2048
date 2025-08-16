allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.diffplug.spotless") version "7.2.1"
    `java-library`
}

spotless {
    kotlin {
        target("**/*.kt")
        targetExclude("${layout.buildDirectory.get()}/**/*.kt")
        ktlint("1.7.1")
    }
}

tasks.named("build") {
    dependsOn("spotlessApply")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
