package za.co.wethinkcode.viewer.app

import RunsBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import za.co.wethinkcode.viewer.app.parse.Commit
import za.co.wethinkcode.viewer.app.parse.LogDetail
import za.co.wethinkcode.viewer.app.parse.RunType.run

class CommitTest {

    val builder = RunsBuilder()

    @Test
    fun `added runs are sorted`() {
        val commit = Commit(builder.commit(3))
        val first = builder.run(1)
        val second = builder.run(2)
        commit.add(second)
        commit.add(first)
        assertThat(commit[0]).isEqualTo(first)
        assertThat(commit[1]).isEqualTo(second)
    }

    @Test
    fun `commit owns runs with same committer and branch and earlier timestamp`() {
        val commit = Commit(builder.commit(2))
        val correct = builder.run(1)
        val wrongCommitter = LogDetail("main", run, "1", "WRONG", "WRONG_EMAIL")
        val wrongBranch = LogDetail("WRONG", run, "1", "geepaw", "geepawmail")
        val wrongTimestamp = builder.run(3)
        assertThat(commit.owns(correct)).isTrue()
        assertThat(commit.owns(wrongCommitter)).isFalse()
        assertThat(commit.owns(wrongTimestamp)).isFalse()
        assertThat(commit.owns(wrongBranch)).isFalse()
    }

    @Test
    fun `empty commit has width of 2`() {
        val commit = Commit(builder.commit())
        assertThat(commit.width).isEqualTo(2)
    }

    @Test
    fun `non-empty commit has width of size + 1`() {
        val run = builder.run()
        val run2 = builder.run()
        val commit = Commit(builder.commit())
        commit.add(run)
        commit.add(run2)
        assertThat(commit.width).isEqualTo(3)
    }
}