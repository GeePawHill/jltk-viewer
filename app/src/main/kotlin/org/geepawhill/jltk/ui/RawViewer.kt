package org.geepawhill.jltk.ui

import javafx.scene.Parent
import org.geepawhill.jltk.ViewerModel
import tornadofx.Fragment
import tornadofx.borderpane
import tornadofx.textarea

class RawViewer(model: ViewerModel) : Fragment() {
    override val root: Parent = borderpane {
        center = textarea(model.raw) {
            font = Styles.font
        }
    }
}