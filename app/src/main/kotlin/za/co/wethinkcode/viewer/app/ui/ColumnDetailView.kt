package za.co.wethinkcode.viewer.app.ui

import javafx.scene.Parent
import javafx.scene.control.Alert
import javafx.scene.layout.Priority
import javafx.stage.DirectoryChooser
import org.geepawhill.jltk.flow.FileHelpers.JLTK_FOLDER
import tornadofx.*
import za.co.wethinkcode.viewer.app.ViewerModel
import za.co.wethinkcode.viewer.app.parse.TestResult
import java.nio.file.Path
import kotlin.io.path.exists

class ColumnDetailView(val model: ViewerModel) : Fragment() {

    val timestamp = label {
        font = Styles.font
    }

    val type = label {
        font = Styles.font
    }
    val branch = label {
        font = Styles.font
    }
    val committer = label {
        font = Styles.font
    }
    val email = label {
        font = Styles.font
    }

    val results = observableListOf<TestResult>()

    override val root: Parent = borderpane {
        minWidth = 400.0
        top = toolbar {
            button("Open...") {
                action {
                    open()
                }
            }
            button("Dog Food") {
                action {
                    model.load(Path.of("..", ".wtc"))
                }
            }
        }
        center = vbox {
            hbox {
                label("Timestamp: ") { font = Styles.font }
                this += timestamp
            }
            hbox {
                label("Type: ") { font = Styles.font }
                this += type
            }
            hbox {
                label("Committer: ") { font = Styles.font }
                this += committer
            }
            hbox {
                label("Branch: ") { font = Styles.font }
                this += branch
            }
            hbox {
                label("Email: ") { font = Styles.font }
                this += email
            }
            listview(results) {
                vgrow = Priority.ALWAYS
                cellFactory = TestResultCellFactory()
            }
        }
    }

    private fun open() {
        val chooser = DirectoryChooser()
        chooser.initialDirectory = Path.of(".").toFile()
        chooser.title = "Select Project Root"
        val result = chooser.showDialog(this.primaryStage) ?: return
        val path = result.toPath()
        val wtcPath = path.resolve(JLTK_FOLDER)
        if (!wtcPath.exists()) {
            val alert = Alert(Alert.AlertType.WARNING, "This folder has no .wtc subfolder.")
            alert.showAndWait()
            return
        }
        model.load(wtcPath)
    }

    init {
        model.columnDetail.addListener { _, _, new ->
            if (new == null) {
                type.text = "none"
            } else {
                type.text = new.detail.type.name
                committer.text = new.detail.committer
                branch.text = new.detail.branch
                email.text = new.detail.email
                timestamp.text = new.detail.timestamp
                results.clear()
                results.addAll(new.tests)
            }
        }
    }
}