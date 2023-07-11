package org.geepawhill.jltk.parse

class TestResultsBySequence : MutableSet<TestResult> by sortedSetOf(RunComparator()) {
    class RunComparator : Comparator<TestResult> {
        override fun compare(first: TestResult?, second: TestResult?): Int {
            if (first == null || second == null) return 0
            return first.sequence.compareTo(second.sequence)
        }
    }

    fun contains(name: String): Boolean = this.filter { it.name == name }.isNotEmpty()
}