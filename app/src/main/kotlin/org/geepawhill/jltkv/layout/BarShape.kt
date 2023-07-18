package org.geepawhill.jltkv.layout

import javafx.scene.paint.Color
import org.geepawhill.jltkv.parse.LogDetail
import org.geepawhill.jltkv.ui.ShapeAdder

class BarShape(
    override val detail: LogDetail,
    lastUpperRight: LogPoint,
    val fill: Color,
    val border: Color
) :
    LogShape {
    override val x = lastUpperRight.x

    val width = 1
    val height = Math.max(1, lastUpperRight.y)

    override fun add(adder: ShapeAdder) {
        adder.makeBar(this, ColumnDetail(detail))
    }
}