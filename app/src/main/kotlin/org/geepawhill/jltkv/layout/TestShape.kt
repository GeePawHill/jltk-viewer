package org.geepawhill.jltkv.layout

import org.geepawhill.jltkv.parse.LogDetail
import org.geepawhill.jltkv.parse.TestResult
import org.geepawhill.jltkv.ui.ShapeAdder


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