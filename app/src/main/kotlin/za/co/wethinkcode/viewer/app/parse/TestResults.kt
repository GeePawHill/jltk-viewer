package za.co.wethinkcode.viewer.app.parse

class TestResults() {
    val results: List<TestResult> get() = resultsWithUnruns()

    private val current = TestResultsBySequence()
    private val old = mutableListOf<String>()
    private var isFirstRun = true

    fun add(name: String, result: TestStatus) {
        current.add(TestResult(name, result, determineNewness(name), determineSequence(name)))
        old.add(name)
    }

    private fun determineSequence(name: String): Int {
        old.indices.forEach {
            if (old[it] == name) return it
        }
        return old.size
    }

    fun add(passes: List<String>, fails: List<String>, disables: List<String>, aborts: List<String>) {
        passes.forEach { add(it, TestStatus.pass) }
        fails.forEach { add(it, TestStatus.fail) }
        disables.forEach { add(it, TestStatus.disable) }
        aborts.forEach { add(it, TestStatus.abort) }
    }

    private fun resultsWithUnruns(): List<TestResult> {
        old.indices.forEach {
            val name = old[it]
            if (!current.contains(name)) {
                current.add(TestResult(name, TestStatus.unrun, false, it))
            }
        }
        return current.toList()
    }

    fun endRun() {
        current.clear()
        isFirstRun = false
    }

    private fun determineNewness(name: String): Boolean {
        if (isFirstRun) return false
        return !old.contains(name)
    }
}