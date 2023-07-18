package org.geepawhill.jltkv.layout

import org.geepawhill.jltkv.parse.LogDetail
import org.geepawhill.jltkv.parse.TestResult


data class ColumnDetail(val detail: LogDetail, val tests: List<TestResult> = emptyList())