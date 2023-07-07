group = "za.co.wethinkcode"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        setUrl("https://oss.sonatype.org/content/repositories/snapshots/")
    }
    mavenCentral()
}


plugins {
    application
    kotlin("jvm") version "1.8.22"
    id("org.openjfx.javafxplugin") version "0.0.14"
}

kotlin {
    jvmToolchain(19)
}

javafx {
    version = "19"
    modules = mutableListOf(
        "javafx.controls",
        "javafx.graphics",
        "javafx.fxml",
        "javafx.web"
    )
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation("org.yaml:snakeyaml:2.0")
    implementation("no.tornado:tornadofx:2.0.0-SNAPSHOT")
    implementation("org.geepawhill:jltk:latest.release")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

application {
    mainClass.set("za.co.wethinkcode.viewer.app.ViewerMainKt")
}
