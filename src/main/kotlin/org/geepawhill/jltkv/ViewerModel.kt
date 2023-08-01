package org.geepawhill.jltkv

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.geepawhill.jltkv.layout.ColumnDetail
import org.geepawhill.jltkv.layout.LogShapes
import org.geepawhill.jltkv.layout.ShapeDesigner
import org.geepawhill.jltkv.parse.Base64Loader
import org.geepawhill.jltkv.parse.LogCollater
import org.geepawhill.jltkv.parse.LogDetail
import org.geepawhill.jltkv.parse.YamlConverter
import tornadofx.observableListOf
import java.nio.file.Path

class ViewerModel {

    val columnDetail = SimpleObjectProperty<ColumnDetail>()
    val records = observableListOf<LogDetail>()
    val logShapes = SimpleObjectProperty<LogShapes>()
    val raw = SimpleStringProperty()

    val height = SimpleIntegerProperty(0)
    var loadPath = Path.of(".")

    fun load(path: Path) {
        loadPath = path
        val yamls = Base64Loader().load(path)
        raw.set(yamls.joinToString(""))
        val entries = YamlConverter().convert(yamls)
        val commits = LogCollater().collate(entries)
        val shapes = ShapeDesigner().design(commits)
        logShapes.set(shapes)
        records.clear()
        records.addAll(entries)
    }

    fun reload() {
        load(loadPath)
    }

    companion object {
        const val PERSON_ONE = "Person One"
        const val PERSON_ONE_EMAIL = "person.one@gmail.com"
        const val PERSON_TWO = "Person Two"
        const val PERSON_TWO_EMAIL = "person.two@gmail.com"
    }

}
