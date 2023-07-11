package org.geepawhill.jltk.layout

import org.geepawhill.jltk.parse.LogDetail
import org.geepawhill.jltk.parse.TestResult
import org.geepawhill.jltk.ui.ShapeAdder


class TestShape(
    override val detail: LogDetail,
    override val x: Int,
    val y: Int,
    val result: TestResult,
    val tests: List<TestResult>
) : LogShape {
    override fun add(adder: ShapeAdder) {
        adder.makeTest(this, org.geepawhill.jltk.layout.ColumnDetail(detail, tests))
    }

}