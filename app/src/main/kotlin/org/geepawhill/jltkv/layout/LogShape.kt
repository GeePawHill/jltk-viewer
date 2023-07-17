package org.geepawhill.jltkv.layout

import org.geepawhill.jltkv.parse.LogDetail
import org.geepawhill.jltkv.ui.ShapeAdder


interface LogShape {
    val x: Int
    val detail: LogDetail

    fun add(adder: ShapeAdder)
}