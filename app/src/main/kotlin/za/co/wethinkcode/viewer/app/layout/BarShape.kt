package za.co.wethinkcode.viewer.app.layout

import javafx.scene.paint.Color
import za.co.wethinkcode.viewer.app.parse.LogDetail
import za.co.wethinkcode.viewer.app.ui.ShapeAdder

class BarShape(override val detail: LogDetail, lastUpperRight: LogPoint, val fill: Color, val border: Color) :
    LogShape {
    override val x = lastUpperRight.x

    val width = 1
    val height = Math.max(1, lastUpperRight.y)

    override fun add(adder: ShapeAdder) {
        adder.makeBar(this, ColumnDetail(detail))
    }
}