package org.geepawhill.jltk

import RunsBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import za.co.wethinkcode.viewer.app.parse.Commit
import za.co.wethinkcode.viewer.app.parse.Commits

class CommitsTest {

    val builder = RunsBuilder()
    val commits = Commits()

    @Test
    fun `commits are kept sorted`() {
        val first = builder.commit()
        val second = builder.commit()
        val firstCommit = Commit(first)
        val secondCommit = Commit(second)
        commits.add(secondCommit)
        commits.add(firstCommit)
        assertThat(commits[0]).isEqualTo(firstCommit)
        assertThat(commits[1]).isEqualTo(secondCommit)
    }

}