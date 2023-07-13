package org.geepawhill.jltk.layout

import org.geepawhill.jltk.parse.LogDetail

class LogShapes(val height: Int, val width: Int) {
    val entries = mutableListOf<LogDetail>()
    val shapes = mutableListOf<LogShape>()
}