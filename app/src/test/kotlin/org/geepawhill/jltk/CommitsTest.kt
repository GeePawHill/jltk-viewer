package org.geepawhill.jltk

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.jltk.parse.Commit
import org.geepawhill.jltk.parse.Commits
import org.junit.jupiter.api.Test

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