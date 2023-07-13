package org.geepawhill.jltk.ui

import javafx.scene.Parent
import org.geepawhill.jltk.ViewerModel
import tornadofx.*

public class ViewerFrame : View() {
    val model = ViewerModel()

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