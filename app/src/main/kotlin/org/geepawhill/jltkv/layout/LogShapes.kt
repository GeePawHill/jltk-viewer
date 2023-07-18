package org.geepawhill.jltkv.layout

import org.geepawhill.jltkv.parse.LogDetail

class LogShapes(val height: Int, val width: Int) {
    val entries = mutableListOf<LogDetail>()
    val shapes = mutableListOf<LogShape>()
}