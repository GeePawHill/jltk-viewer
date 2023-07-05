package za.co.wethinkcode.viewer.app

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import za.co.wethinkcode.viewer.app.parse.Base64Loader
import java.nio.file.Path

class Base64LoaderTest {

    val loader = Base64Loader()

    @Test
    fun `non-existent file makes one record`() {
        val results = loader.load(Path.of("does", "not", "exist"))
        assertThat(results.size).isEqualTo(1)
    }

    @Test
    fun `not a folder makes one record`() {
        val results = loader.load(Path.of("testData", ".wtc", "known-good.wtc"))
        assertThat(results.size).isEqualTo(1)
    }

    @Test
    fun `finds only files ending in wtc`() {
        val results = loader.load(Path.of("testData", ".wtc"))
        assertThat(results.size).isEqualTo(4)
    }

    @Test
    fun `makes error on known-bad with no date`() {
        val runs = mutableListOf<String>()
        loader.safeLoad(runs, Path.of("testData", ".wtc", "known-bad.wtc"))
        assertThat(runs.size).isEqualTo(1)
        val lines = runs[0].split("\n")
        assertThat(lines[1]).isEqualTo("type: base64")
        assertThat(lines[2]).isEqualTo("timestamp: '2020-01-01T00:00:00'")
    }

    @Test
    fun `makes error on known-bad with valid filestamp`() {
        val runs = mutableListOf<String>()
        loader.safeLoad(runs, Path.of("testData", ".wtc", "branch_committer_20230409125801.wtc"))
        assertThat(runs.size).isEqualTo(1)
        val lines = runs[0].split("\n")
        assertThat(lines[1]).isEqualTo("type: base64")
        assertThat(lines[2]).isEqualTo("timestamp: '2023-04-09T12:58:01'")
    }

    @Test
    fun `parses known-good`() {
        val runs = mutableListOf<String>()
        loader.safeLoad(runs, Path.of("testData", ".wtc", "known-good-two-records.wtc"))
        assertThat(runs.size).isEqualTo(2)
        println(runs[0])
        println(runs[1])
//        val lines = runs[0].split("\n")
//        assertThat(lines[1]).isEqualTo("type: base64")
    }

}