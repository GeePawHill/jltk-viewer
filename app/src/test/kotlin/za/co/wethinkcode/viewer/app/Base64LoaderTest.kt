package za.co.wethinkcode.viewer.app

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.jltk.flow.FileHelpers.JLTK_FOLDER
import org.junit.jupiter.api.Test
import za.co.wethinkcode.viewer.app.parse.Base64Loader
import java.nio.file.Path

class Base64LoaderTest {

    val loader = Base64Loader()

    @Test
    fun `non-existent path makes one record`() {
        val results = loader.load(Path.of("does", "not", "exist"))
        assertThat(results.size).isEqualTo(1)
    }

    @Test
    fun `path not a folder makes one record`() {
        val results = loader.load(Path.of("testData", "notAFolder.txt"))
        assertThat(results.size).isEqualTo(1)
    }

    @Test
    fun `finds only files ending in the key suffices`() {
        val results = loader.load(Path.of("testData", "twoFinalsOneTemp", JLTK_FOLDER))
        assertThat(results.size).isEqualTo(3)
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
        loader.safeLoad(runs, Path.of("testData", ".jltk", "branch_committer_20230409125801.jltl"))
        assertThat(runs.size).isEqualTo(1)
        val lines = runs[0].split("\n")
        assertThat(lines[1]).isEqualTo("type: base64")
        assertThat(lines[2]).isEqualTo("timestamp: '2023-04-09T12:58:01'")
    }

    @Test
    fun `parses known-good`() {
        val runs = mutableListOf<String>()
        loader.safeLoad(runs, Path.of("testData", JLTK_FOLDER, "known-good-two-records.jltl"))
        assertThat(runs.size).isEqualTo(2)
        println(runs[0])
        println(runs[1])
//        val lines = runs[0].split("\n")
//        assertThat(lines[1]).isEqualTo("type: base64")
    }

}