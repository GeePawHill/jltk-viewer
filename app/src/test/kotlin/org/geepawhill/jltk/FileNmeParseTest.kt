package org.geepawhill.jltk

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.jltk.parse.ParsedFileName
import org.geepawhill.jltk.parse.ParsedFileName.Companion.EARLIEST_FILESTAMP
import org.geepawhill.jltk.parse.ParsedFileName.Companion.EARLIEST_TIMESTAMP
import org.geepawhill.jltk.parse.ParsedFileName.Companion.NA
import org.junit.jupiter.api.Test

class FileNmeParseTest {
    @Test
    fun `garbage fails with defaults`() {
        val parse = ParsedFileName("asdfasdf")
        assertThat(parse.filestamp).isEqualTo(EARLIEST_FILESTAMP)
        assertThat(parse.timestamp).isEqualTo(EARLIEST_TIMESTAMP)
        assertThat(parse.branch).isEqualTo(NA)
        assertThat(parse.email).isEqualTo(NA)
    }

    @Test
    fun `good file succeeds`() {
        val parse = ParsedFileName("branch_geepaw_20230409121109.wtc")
        assertThat(parse.filestamp).isEqualTo("20230409121109")
        assertThat(parse.timestamp).isEqualTo("2023-04-09T12:11:09")
        assertThat(parse.branch).isEqualTo("branch")
        assertThat(parse.email).isEqualTo("geepaw")
    }

    @Test
    fun `bad date does correct thing`() {
        val parse = ParsedFileName("branch_geepaw_00000000000000.wtc")
        assertThat(parse.filestamp).isEqualTo("00000000000000")
        assertThat(parse.timestamp).isEqualTo(EARLIEST_TIMESTAMP)
        assertThat(parse.branch).isEqualTo("branch")
        assertThat(parse.email).isEqualTo("geepaw")
    }
}