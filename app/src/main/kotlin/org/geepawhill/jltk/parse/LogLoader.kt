package org.geepawhill.jltk.parse

import org.geepawhill.jltk.layout.LogShapes
import org.geepawhill.jltk.layout.ShapeDesigner
import java.nio.file.Path

class LogLoader {
    fun fromFolder(path: Path): LogShapes {
        val yamls = Base64Loader().load(path)
        val entries = YamlConverter().convert(yamls)
        val commits = LogCollater().collate(entries)
        return ShapeDesigner().design(commits)
    }
}