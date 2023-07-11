package org.geepawhill.jltk.layout

import org.geepawhill.jltk.parse.LogDetail
import org.geepawhill.jltk.parse.TestResult


data class ColumnDetail(val detail: LogDetail, val tests: List<TestResult> = emptyList())