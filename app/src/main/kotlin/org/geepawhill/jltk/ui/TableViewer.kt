package org.geepawhill.jltk.ui

import javafx.scene.Parent
import org.geepawhill.jltk.parse.LogDetail
import tornadofx.Fragment
import tornadofx.borderpane
import tornadofx.readonlyColumn
import tornadofx.tableview

class TableViewer(model: org.geepawhill.jltk.ViewerModel) : Fragment() {
    override val root: Parent = borderpane {
        center = tableview(model.records) {
            readonlyColumn("Summary", LogDetail::summary) {
                minWidth = 100.0
            }
            readonlyColumn("Timestamp", LogDetail::timestamp) {
                minWidth = 200.0
            }
            readonlyColumn("Type", LogDetail::type) {
                minWidth = 50.0
            }
            readonlyColumn("Committer", LogDetail::committer) {
                minWidth = 300.0
            }

        }
    }
}
