package org.geepawhill.jltkv

import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import org.geepawhill.jltk.flow.Recorder

public class ViewerMain : Application() {
    companion object {
        val wtc = Recorder().logRun()
    }

    override fun start(primaryStage: Stage?) {
        val label = Label("Hello.")
        val pane = BorderPane()
        pane.center = label
        val scene = Scene(pane)
        primaryStage!!.scene = scene
        primaryStage!!.show()
    }
}


fun main(args: Array<String>) {
    launch(ViewerMain::class.java)
}
