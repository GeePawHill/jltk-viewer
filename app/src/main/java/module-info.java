module jltk.viewer.app.main {
    exports org.geepawhill.jltk.ui;
    opens org.geepawhill.jltk to javafx.graphics;
    requires jltk;
    requires kotlin.stdlib;
    requires javafx.base;
    requires javafx.controls;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.web;
    requires tornadofx;
    requires org.yaml.snakeyaml;
    requires org.slf4j.nop;
}