package org.geepawhill.jltkv

import org.geepawhill.jltkv.ui.ViewerFrame
import tornadofx.App
import tornadofx.launch
import za.co.wethinkcode.flow.Recorder

public class ViewerMain : App(ViewerFrame::class) {
    companion object {
        val wtc = Recorder().logRun()
    }
}


fun main(args: Array<String>) {
    launch<ViewerMain>(args)
}
