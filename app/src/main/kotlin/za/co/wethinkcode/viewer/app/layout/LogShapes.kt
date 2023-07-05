package za.co.wethinkcode.viewer.app.layout

import za.co.wethinkcode.viewer.app.parse.LogDetail

class LogShapes(val height: Int, val width: Int) {
    val entries: List<LogDetail> get() = shapes.map { it.detail }
    val shapes = mutableListOf<LogShape>()
}