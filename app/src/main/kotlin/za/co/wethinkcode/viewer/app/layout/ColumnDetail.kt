package za.co.wethinkcode.viewer.app.layout

import za.co.wethinkcode.viewer.app.parse.LogDetail
import za.co.wethinkcode.viewer.app.parse.TestResult

data class ColumnDetail(val detail: LogDetail, val tests: List<TestResult> = emptyList())