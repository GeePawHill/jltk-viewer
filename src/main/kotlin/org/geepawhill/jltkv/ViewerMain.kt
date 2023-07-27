package org.geepawhill.jltkv

import org.geepawhill.jltk.flow.Recorder
import tornadofx.App
import tornadofx.launch

public class ViewerMain : App(ViewerFrame::class) {
    companion object {
        val wtc = Recorder().logRun()
    }
}


fun main(args: Array<String>) {
    launch<ViewerMain>(args)
}
