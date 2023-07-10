package za.co.wethinkcode.viewer.app

import org.geepawhill.jltk.flow.Recorder
import tornadofx.App
import tornadofx.launch
import za.co.wethinkcode.viewer.app.ui.ViewerFrame

public class ViewerMain : App(ViewerFrame::class) {
    companion object {
        val wtc = Recorder().logRun()
    }
}


fun main(args: Array<String>) {
    launch<ViewerMain>(args)
}
