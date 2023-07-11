package org.geepawhill.jltk.layout

import org.geepawhill.jltk.parse.LogDetail

class LogShapes(val height: Int, val width: Int) {
    val entries: List<LogDetail> get() = shapes.map { it.detail }
    val shapes = mutableListOf<LogShape>()
}