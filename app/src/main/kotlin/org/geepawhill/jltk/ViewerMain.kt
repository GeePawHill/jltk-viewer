package org.geepawhill.jltk

import org.geepawhill.jltk.flow.Recorder
import org.geepawhill.jltk.ui.ViewerFrame
import tornadofx.App
import tornadofx.launch

public class ViewerMain : App(ViewerFrame::class) {
    companion object {
        val wtc = Recorder().logRun()
    }
}


fun main(args: Array<String>) {
    launch<org.geepawhill.jltk.ViewerMain>(args)
}
