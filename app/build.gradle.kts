plugins {
    kotlin("jvm") version "2.2.0"
    application
}

dependencies {
    implementation(project(":core"))
    implementation(project(":utilities"))
    implementation(project(":ui"))
    implementation(project(":ai"))
}

application {
    mainClass.set("org.example.AppKt")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
