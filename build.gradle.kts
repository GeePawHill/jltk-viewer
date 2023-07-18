repositories {
    mavenCentral()
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
