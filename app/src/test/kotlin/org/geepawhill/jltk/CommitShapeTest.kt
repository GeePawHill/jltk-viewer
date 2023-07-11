package org.geepawhill.jltk

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.jltk.parse.Commit
import org.junit.jupiter.api.Test

class CommitShapeTest {

    val runs = RunsBuilder()

    @Test
    fun `empty commit at left`() {
        runs.commit()
        val commit = Commit(runs[0])
        val lastUpperRight = org.geepawhill.jltk.layout.LogPoint(0, 0)
        val shape = org.geepawhill.jltk.layout.CommitShape(commit, lastUpperRight)
        assertThat(shape.x).isEqualTo(0)
        assertThat(shape.width).isEqualTo(2)
        assertThat(shape.height).isEqualTo(1)
    }

    @Test
    fun `empty commit not at left`() {
        runs.commit()
        val commit = Commit(runs[0])
        val lastUpperRight = org.geepawhill.jltk.layout.LogPoint(5, 0)
        val shape = org.geepawhill.jltk.layout.CommitShape(commit, lastUpperRight)
        assertThat(shape.x).isEqualTo(5)
        assertThat(shape.width).isEqualTo(2)
        assertThat(shape.height).isEqualTo(1)
    }

    @Test
    fun `empty commit honors left-most column`() {
        runs.commit()
        val commit = Commit(runs[0])
        val lastUpperRight = org.geepawhill.jltk.layout.LogPoint(0, 5)
        val shape = org.geepawhill.jltk.layout.CommitShape(commit, lastUpperRight)
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
        val lastUpperRight = org.geepawhill.jltk.layout.LogPoint(0, 0)
        val shape = org.geepawhill.jltk.layout.CommitShape(commit, lastUpperRight)
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
        val lastUpperRight = org.geepawhill.jltk.layout.LogPoint(0, 4)
        val shape = org.geepawhill.jltk.layout.CommitShape(commit, lastUpperRight)
        assertThat(shape.x).isEqualTo(0)
        assertThat(shape.width).isEqualTo(2)
        assertThat(shape.height).isEqualTo(5)
    }
}