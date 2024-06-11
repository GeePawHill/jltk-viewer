group = "org.geepawhill"
version = "1.0-SNAPSHOT"

val javaFXVersion = "18"

repositories {
    mavenCentral()
    maven {
        setUrl("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

val helper = configurations.create("helper")

dependencies {
    helper("org.geepawhill:jltk-monitor:0.0.12")
}

task<Copy>("get-jltk-monitor") {
    from(configurations["helper"])
    into(layout.projectDirectory.dir(".jltk"))
    rename {
        "jltk-monitor.jar"
    }
}

plugins {
    application
    kotlin("jvm") version "1.8.22"
    id("org.openjfx.javafxplugin") version "0.0.14"
    id("dev.hydraulic.conveyor") version "1.6"
}

kotlin {
    jvmToolchain(18)
}

java {
    version = JavaLanguageVersion.of(JavaVersion.VERSION_18.majorVersion)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
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

dependencies {
    implementation("org.yaml:snakeyaml:2.0")
    implementation("org.geepawhill:jltk:0.0.59")
    testImplementation("org.assertj:assertj-core:3.23.1")
    implementation("org.junit.jupiter:junit-jupiter:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    implementation("org.slf4j:slf4j-nop:2.0.7")
    implementation("no.tornado:tornadofx:2.0.0-SNAPSHOT")
}

application {
    mainClass.set("org.geepawhill.jltkv.ViewerMainKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

