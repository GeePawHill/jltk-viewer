package org.geepawhill.jltk

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.geepawhill.jltk.layout.ShapeDesigner
import org.geepawhill.jltk.parse.Base64Loader
import org.geepawhill.jltk.parse.LogCollater
import org.geepawhill.jltk.parse.LogDetail
import org.geepawhill.jltk.parse.YamlConverter
import tornadofx.observableListOf
import java.nio.file.Path

class ViewerModel {

    val columnDetail = SimpleObjectProperty<org.geepawhill.jltk.layout.ColumnDetail>()
    val records = observableListOf<LogDetail>()
    val logShapes = SimpleObjectProperty<org.geepawhill.jltk.layout.LogShapes>()
    val raw = SimpleStringProperty()

    val height = SimpleIntegerProperty(0)

    fun load(path: Path) {
        val yamls = Base64Loader().load(path)
        raw.set(yamls.joinToString(""))
        val entries = YamlConverter().convert(yamls)
        val commits = LogCollater().collate(entries)
        val shapes = ShapeDesigner().design(commits)
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
