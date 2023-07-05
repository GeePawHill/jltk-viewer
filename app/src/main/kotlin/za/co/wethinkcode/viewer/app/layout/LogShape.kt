package za.co.wethinkcode.viewer.app.layout

import za.co.wethinkcode.viewer.app.parse.LogDetail
import za.co.wethinkcode.viewer.app.ui.ShapeAdder

interface LogShape {
    val x: Int
    val detail: LogDetail

    fun add(adder: ShapeAdder)
}