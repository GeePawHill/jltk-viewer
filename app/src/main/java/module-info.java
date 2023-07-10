module jltk.viewer.app.main {
    exports za.co.wethinkcode.viewer.app.ui;
    opens za.co.wethinkcode.viewer.app to javafx.graphics;
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