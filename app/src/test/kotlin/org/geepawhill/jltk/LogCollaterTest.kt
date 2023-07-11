import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import za.co.wethinkcode.viewer.app.parse.LogCollater
import za.co.wethinkcode.viewer.app.parse.RunType
import za.co.wethinkcode.viewer.app.parse.RunType.commit
import za.co.wethinkcode.viewer.app.parse.RunType.run

class LogCollaterTest {
    val runs = RunsBuilder()
    val collater = LogCollater()

    @Test
    fun `one commit yields one commit with no runs`() {
        runs.commit()
        val commits = collater.collate(runs.toList())
        assertThat(commits).hasSize(1)
        assertThat(commits[0].detail.type).isEqualTo(commit)
        assertThat(commits[0]).isEmpty()
    }

    @Test
    fun `empty yields empty`() {
        val commits = collater.collate(runs.toList())
        assertThat(commits).isEmpty()
    }

    @Test
    fun `one run one commit yields one Commit`() {
        runs.run()
        runs.commit()
        val result = collater.collate(runs.toList())
        assertThat(result).hasSize(1)
        assertThat(result[0].detail.type).isEqualTo(commit)
        assertThat(result[0][0].type).isEqualTo(run)
    }

    @Test
    fun `run commit run commit forms two commits`() {
        runs.run()
        runs.commit()
        runs.run()
        runs.commit()
        val result = collater.collate(runs.toList())
        assertThat(result).hasSize(2)
        assertThat(result[0][0].type).isEqualTo(run)
        assertThat(result[0][0].timestamp).isEqualTo("1")
        assertThat(result[1][0].type).isEqualTo(run)
    }

    @Test
    fun `makes local commit if needed`() {
        runs.run()
        runs.run()
        val result = collater.collate(runs.toList())
        assertThat(result).hasSize(1)
        assertThat(result[0].detail.type).isEqualTo(RunType.local)
        assertThat(result[0].size).isEqualTo(2)
    }
}