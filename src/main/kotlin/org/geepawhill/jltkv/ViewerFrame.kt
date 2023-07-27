package org.geepawhill.jltkv

import javafx.scene.Parent
import tornadofx.View
import tornadofx.borderpane
import tornadofx.label
import tornadofx.vbox

class ViewerFrame : View() {
    override val root: Parent = borderpane {
        center = vbox {
            label("Hello!")
        }
    }
}