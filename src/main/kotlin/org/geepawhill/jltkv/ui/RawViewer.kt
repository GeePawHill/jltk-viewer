package org.geepawhill.jltkv.ui

import javafx.scene.Parent
import tornadofx.Fragment
import tornadofx.borderpane
import tornadofx.textarea

class RawViewer(model: org.geepawhill.jltkv.ViewerModel) : Fragment() {
    override val root: Parent = borderpane {
        center = textarea(model.raw) {
            font = Styles.font
        }
    }
}