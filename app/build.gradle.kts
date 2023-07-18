group = "za.co.wethinkcode"
version = "1.0-SNAPSHOT"


//Resolve the used operating system
var currentOS = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem()
var platform = ""
if (currentOS.isMacOsX) {
    platform = "mac"
} else if (currentOS.isLinux) {
    platform = "linux"
} else if (currentOS.isWindows) {
    platform = "win"
}

val javaFXVersion = "18"

repositories {
    mavenCentral()
}


plugins {
    application
    kotlin("jvm") version "1.8.22"
    //   id("org.beryx.runtime") version "1.13.0"
}

kotlin {
    jvmToolchain(18)
}

java {
    version = JavaLanguageVersion.of(JavaVersion.VERSION_18.majorVersion)
}

dependencies {
    implementation("org.yaml:snakeyaml:2.0")
    implementation("org.geepawhill:jltk:latest.release")
    testImplementation("org.assertj:assertj-core:3.23.1")
    implementation("org.junit.jupiter:junit-jupiter:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    implementation("org.slf4j:slf4j-nop:2.0.7")
    implementation("org.openjfx:javafx-base:${javaFXVersion}:${platform}")
    implementation("org.openjfx:javafx-controls:${javaFXVersion}:${platform}")
    implementation("org.openjfx:javafx-graphics:${javaFXVersion}:${platform}")
}

application {
    mainClass.set("org.geepawhill.jltkv.ViewerMainKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
