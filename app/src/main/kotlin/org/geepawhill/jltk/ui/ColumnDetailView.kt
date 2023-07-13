package org.geepawhill.jltk.ui

import javafx.scene.Parent
import javafx.scene.control.Alert
import javafx.scene.layout.Priority
import javafx.stage.DirectoryChooser
import org.geepawhill.jltk.flow.FileHelpers.JLTK_FOLDER
import org.geepawhill.jltk.parse.TestResult
import tornadofx.*
import java.nio.file.Path
import kotlin.io.path.exists

class ColumnDetailView(val model: org.geepawhill.jltk.ViewerModel) : Fragment() {

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
                    model.load(Path.of("..", JLTK_FOLDER))
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
        chooser.title = "Select Git Root"
        val result = chooser.showDialog(this.primaryStage) ?: return
        val path = result.toPath()
        val jltkPath = path.resolve(JLTK_FOLDER)
        if (!jltkPath.exists()) {
            val alert = Alert(Alert.AlertType.WARNING, "This folder has no $JLTK_FOLDER subfolder.")
            alert.showAndWait()
            return
        }
        model.load(jltkPath)
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