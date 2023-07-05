package za.co.wethinkcode.viewer.app.layout

import za.co.wethinkcode.viewer.app.parse.LogDetail
import za.co.wethinkcode.viewer.app.parse.TestResult
import za.co.wethinkcode.viewer.app.ui.ShapeAdder

class TestShape(
    override val detail: LogDetail,
    override val x: Int,
    val y: Int,
    val result: TestResult,
    val tests: List<TestResult>
) : LogShape {
    override fun add(adder: ShapeAdder) {
        adder.makeTest(this, ColumnDetail(detail, tests))
    }

}