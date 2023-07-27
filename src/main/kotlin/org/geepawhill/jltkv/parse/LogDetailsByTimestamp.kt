package org.geepawhill.jltkv.parse

class LogDetailsByTimestamp : MutableSet<LogDetail> by sortedSetOf(RunComparator()) {
    class RunComparator : Comparator<LogDetail> {
        override fun compare(first: LogDetail?, second: LogDetail?): Int {
            if (first == null || second == null) return 0
            return first.timestamp.compareTo(second.timestamp)
        }
    }
}