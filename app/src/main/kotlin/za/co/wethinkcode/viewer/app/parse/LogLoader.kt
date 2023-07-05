package za.co.wethinkcode.viewer.app.parse

import za.co.wethinkcode.viewer.app.layout.LogShapes
import za.co.wethinkcode.viewer.app.layout.ShapeDesigner
import java.nio.file.Path

class LogLoader {
    fun fromFolder(path: Path): LogShapes {
        val yamls = Base64Loader().load(path)
        val entries = YamlConverter().convert(yamls)
        val commits = LogCollater().collate(entries)
        return ShapeDesigner().design(commits)
    }
}