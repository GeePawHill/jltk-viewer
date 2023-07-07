package za.co.wethinkcode.viewer.app.parse

import java.nio.file.Path
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ParsedFileName(val name: String) {
    val branch: String
    val email: String
    val timestamp: String
    val filestamp: String

    init {
        val path = Path.of(name)
        val file = path.fileName.toString()
        val regex = Regex("(.*)_(.*)_(\\d{14})(.*)")
        val result = regex.find(file)
        if (result == null) {
            branch = NA
            email = NA
            filestamp = EARLIEST_FILESTAMP
            timestamp = timestampFrom(filestamp)
        } else {
            branch = result.groups[1]!!.value
            email = result.groups[2]!!.value
            filestamp = result.groups[3]!!.value
            timestamp = timestampFrom(filestamp)
        }
    }

    companion object {
        const val NA = "N/A"
        const val EARLIEST_FILESTAMP = "20200101000000"
        val EARLIEST_TIMESTAMP = timestampFrom(LocalDateTime.of(2020, 1, 1, 0, 0, 0))

        fun timestampFrom(filestamp: String): String {
            try {
                val time = filetimeFormatter.parse(filestamp, LocalDateTime::from)
                return timestampFrom(time)
            } catch (ignored: Exception) {
                return EARLIEST_TIMESTAMP
            }
        }

        fun timestampFrom(time: LocalDateTime): String {
            return time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).split("\\.")[0]
        }

        var filetimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddkkmmss")
    }
}