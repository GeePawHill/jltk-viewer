package org.geepawhill.jltkv

import org.geepawhill.jltkv.parse.LogDetail
import org.geepawhill.jltkv.parse.RunType

class RunsBuilder : MutableList<LogDetail> by mutableListOf() {
    var sequence = 1

    fun commit(altSequence: Int = sequence): LogDetail {
        val run = LogDetail("main", RunType.commit, "$altSequence", "geepaw", "geepawmail")
        this.add(run)
        sequence += 1
        return run
    }

    fun run(altSequence: Int = sequence): LogDetail {
        val run = LogDetail("main", RunType.run, "$altSequence", "geepaw", "geepawmail")
        this.add(run)
        sequence += 1
        return run
    }

    fun test(testCount: Int, altSequence: Int = sequence): LogDetail {
        val passList = mutableListOf<String>()
        (0 until testCount).forEach {
            passList += "Test $it"
        }
        val run = LogDetail(
            "main", RunType.test, "$altSequence", "geepaw", "geepawmail",
            passList
        )
        this.add(run)
        sequence += 1
        return run
    }
}