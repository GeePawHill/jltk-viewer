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
        "javafx.web",
        "javafx.swing"
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
    implementation("org.junit.jupiter:junit-jupiter:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    implementation("org.slf4j:slf4j-nop:2.0.7")
    implementation("org.slf4j:slf4j-nop:2.0.6")
}

application {
    mainModule.set("jltk.viewer.app.main")
    mainClass.set("za.co.wethinkcode.viewer.app.ViewerMainKt")
}
