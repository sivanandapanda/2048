plugins {
    kotlin("jvm") version "2.2.0"
}

dependencies {
    implementation(project(":core"))

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.jupiter.launcher)
}

tasks.test {
    useJUnitPlatform()
}