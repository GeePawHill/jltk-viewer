package za.co.wethinkcode.viewer.app.ui

import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.util.Callback
import za.co.wethinkcode.viewer.app.parse.TestResult

class TestResultCellFactory : Callback<ListView<TestResult>, ListCell<TestResult>> {
    override fun call(p0: ListView<TestResult>?): ListCell<TestResult> {
        return TestResultCell()
    }
}