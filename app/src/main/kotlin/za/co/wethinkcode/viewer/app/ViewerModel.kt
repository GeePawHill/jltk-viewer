package za.co.wethinkcode.viewer.app

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*
import za.co.wethinkcode.viewer.app.layout.ColumnDetail
import za.co.wethinkcode.viewer.app.layout.LogShapes
import za.co.wethinkcode.viewer.app.parse.LogDetail
import za.co.wethinkcode.viewer.app.parse.LogLoader
import java.nio.file.Path

class ViewerModel {

    val columnDetail = SimpleObjectProperty<ColumnDetail>()
    val records = observableListOf<LogDetail>()
    val logShapes = SimpleObjectProperty<LogShapes>()
    val builder = LogLoader()

    val height = SimpleIntegerProperty(0)

    fun load(path: Path) {
        val shapes = builder.fromFolder(path)
        logShapes.set(shapes)
        records.clear()
        records.addAll(shapes.entries)
    }

    companion object {
        const val PERSON_ONE = "Person One"
        const val PERSON_ONE_EMAIL = "person.one@gmail.com"
        const val PERSON_TWO = "Person Two"
        const val PERSON_TWO_EMAIL = "person.two@gmail.com"
    }

}
