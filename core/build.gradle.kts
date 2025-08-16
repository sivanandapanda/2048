plugins {
    kotlin("jvm") version "2.2.0"
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.jupiter.launcher)
}

tasks.test {
    useJUnitPlatform()
}