package za.co.wethinkcode.viewer.app.ui

import javafx.scene.Parent
import tornadofx.*
import za.co.wethinkcode.viewer.app.ViewerModel

class ViewerFrame : View() {
    val model = ViewerModel()

    override val root: Parent = borderpane {
        center = tabpane {
            tab("Tree") {
                this += GraphicViewer(model)
            }
            tab("Tabular") {
                this += TableViewer(model)
            }
        }
    }

}