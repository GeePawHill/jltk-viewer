package org.geepawhill.jltk.layout

import javafx.scene.paint.Color
import org.geepawhill.jltk.parse.LogDetail
import org.geepawhill.jltk.ui.ShapeAdder

class BarShape(
    override val detail: LogDetail,
    lastUpperRight: org.geepawhill.jltk.layout.LogPoint,
    val fill: Color,
    val border: Color
) :
    org.geepawhill.jltk.layout.LogShape {
    override val x = lastUpperRight.x

    val width = 1
    val height = Math.max(1, lastUpperRight.y)

    override fun add(adder: ShapeAdder) {
        adder.makeBar(this, org.geepawhill.jltk.layout.ColumnDetail(detail))
    }
}