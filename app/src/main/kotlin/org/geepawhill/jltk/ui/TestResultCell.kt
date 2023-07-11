package org.geepawhill.jltk.ui

import javafx.scene.control.ListCell
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.text.Font
import org.geepawhill.jltk.parse.TestResult

class TestResultCell : ListCell<TestResult>() {
    override fun updateItem(result: TestResult?, p1: Boolean) {
        super.updateItem(result, p1)
        if (isEmpty || result == null) {
            text = ""
        } else {
            text = result.name
            textFill = result.textColor
            background = chooseBackground(result)
            font = Font.font(20.0)
        }
    }

    private fun chooseBackground(result: TestResult): Background {
        return Background(BackgroundFill(result.color, null, null))
    }
}