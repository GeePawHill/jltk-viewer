package za.co.wethinkcode.viewer.app.ui

import javafx.scene.Parent
import tornadofx.*
import za.co.wethinkcode.viewer.app.ViewerModel
import za.co.wethinkcode.viewer.app.parse.LogDetail

class TableViewer(model: ViewerModel) : Fragment() {
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
