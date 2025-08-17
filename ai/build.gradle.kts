plugins {
    kotlin("jvm") version "2.2.0"
}

dependencies {
    implementation(project(":utilities"))

    implementation(libs.jackson.kotlin)
}