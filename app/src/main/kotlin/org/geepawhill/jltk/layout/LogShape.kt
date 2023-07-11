package org.geepawhill.jltk.layout

import org.geepawhill.jltk.parse.LogDetail
import org.geepawhill.jltk.ui.ShapeAdder


interface LogShape {
    val x: Int
    val detail: LogDetail

    fun add(adder: ShapeAdder)
}