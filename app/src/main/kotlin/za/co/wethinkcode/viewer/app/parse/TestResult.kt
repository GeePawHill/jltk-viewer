package za.co.wethinkcode.viewer.app.parse

import javafx.scene.paint.Color
import javafx.scene.paint.Paint

data class TestResult(val name: String, val status: TestStatus, val isNew: Boolean, val sequence: Int) {
    val textColor: Paint = when (isNew) {
        true -> Color.WHITE
        false -> Color.BLACK
    }
    
    val color: Paint =
        when (status) {
            TestStatus.pass -> Color.GREEN
            TestStatus.fail -> Color.RED
            TestStatus.disable -> Color.YELLOW
            TestStatus.abort -> Color.BLUE
            TestStatus.unrun -> Color.GRAY
        }
}
