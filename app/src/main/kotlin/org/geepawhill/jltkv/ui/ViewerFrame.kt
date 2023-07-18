package org.geepawhill.jltkv.ui

import javafx.scene.Parent
import tornadofx.*

public class ViewerFrame : View() {
    val model = org.geepawhill.jltkv.ViewerModel()

    override val root: Parent = borderpane {
        center = tabpane {
            tab("Tree") {
                this += GraphicViewer(model)
            }
            tab("Tabular") {
                this += TableViewer(model)
            }
            tab("Raw") {
                this += RawViewer(model)
            }
        }
    }

}