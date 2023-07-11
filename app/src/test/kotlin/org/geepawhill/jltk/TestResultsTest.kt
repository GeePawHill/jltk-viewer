package org.geepawhill.jltk

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import za.co.wethinkcode.viewer.app.parse.TestResult
import za.co.wethinkcode.viewer.app.parse.TestResults
import za.co.wethinkcode.viewer.app.parse.TestStatus
import za.co.wethinkcode.viewer.app.parse.TestStatus.unrun

class TestResultsTest {
    val results = TestResults()

    @Test
    fun `tests are sorted by time of addition`() {
        results.add("b", TestStatus.pass)
        results.add("a", TestStatus.pass)
        assertThat(results.results[0].name).isEqualTo("b")
        assertThat(results.results[1].name).isEqualTo("a")
    }

    @Test
    fun `on first run, all tests are old`() {
        results.add("b", TestStatus.pass)
        results.add("a", TestStatus.pass)
        assertThat(results.results[0].isNew).isFalse()
        assertThat(results.results[1].isNew).isFalse()
    }

    @Test
    fun `on any other run, new tests are new`() {
        results.endRun()
        results.add("new", TestStatus.pass)
        assertThat(results.results[0].isNew).isTrue()
    }

    @Test
    fun `old tests are old after endRun`() {
        results.add("old", TestStatus.pass)
        results.endRun()
        results.add("old", TestStatus.pass)
        assertThat(results.results[0].isNew).isFalse()
    }

    @Test
    fun `old tests are unrun in results after endRun`() {
        results.add("old", TestStatus.pass)
        results.endRun()
        assertThat(results.results).containsExactly(TestResult("old", unrun, false, 0))
    }

}