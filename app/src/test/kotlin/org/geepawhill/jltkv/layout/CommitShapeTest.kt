package org.geepawhill.jltkv.layout

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.jltkv.RunsBuilder
import org.geepawhill.jltkv.parse.Commit
import org.junit.jupiter.api.Test

class CommitShapeTest {

    val runs = RunsBuilder()

    @Test
    fun `empty commit at left`() {
        runs.commit()
        val commit = Commit(runs[0])
        val lastUpperRight = LogPoint(0, 0)
        val shape = CommitShape(commit, lastUpperRight)
        assertThat(shape.x).isEqualTo(0)
        assertThat(shape.width).isEqualTo(2)
        assertThat(shape.height).isEqualTo(1)
    }

    @Test
    fun `empty commit not at left`() {
        runs.commit()
        val commit = Commit(runs[0])
        val lastUpperRight = LogPoint(5, 0)
        val shape = CommitShape(commit, lastUpperRight)
        assertThat(shape.x).isEqualTo(5)
        assertThat(shape.width).isEqualTo(2)
        assertThat(shape.height).isEqualTo(1)
    }

    @Test
    fun `empty commit honors left-most column`() {
        runs.commit()
        val commit = Commit(runs[0])
        val lastUpperRight = LogPoint(0, 5)
        val shape = CommitShape(commit, lastUpperRight)
        assertThat(shape.x).isEqualTo(0)
        assertThat(shape.width).isEqualTo(2)
        assertThat(shape.height).isEqualTo(6)
    }

    @Test
    fun `one run wide commit at left`() {
        runs.run()
        runs.commit()
        val commit = Commit(runs[1])
        commit.add(runs[0])
        val lastUpperRight = LogPoint(0, 0)
        val shape = CommitShape(commit, lastUpperRight)
        assertThat(shape.x).isEqualTo(0)
        assertThat(shape.width).isEqualTo(2)
        assertThat(shape.height).isEqualTo(1)
    }

    @Test
    fun `taller test makes taller commit`() {
        runs.test(3)
        runs.commit()
        val commit = Commit(runs[1])
        commit.add(runs[0])
        val lastUpperRight = LogPoint(0, 4)
        val shape = CommitShape(commit, lastUpperRight)
        assertThat(shape.x).isEqualTo(0)
        assertThat(shape.width).isEqualTo(2)
        assertThat(shape.height).isEqualTo(5)
    }
}