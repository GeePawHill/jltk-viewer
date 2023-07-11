package org.geepawhill.jltk

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import org.geepawhill.jltk.parse.LogDetail
import org.geepawhill.jltk.parse.LogLoader
import tornadofx.observableListOf
import java.nio.file.Path

class ViewerModel {

    val columnDetail = SimpleObjectProperty<org.geepawhill.jltk.layout.ColumnDetail>()
    val records = observableListOf<LogDetail>()
    val logShapes = SimpleObjectProperty<org.geepawhill.jltk.layout.LogShapes>()
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
