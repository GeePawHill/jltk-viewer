package org.geepawhill.jltk

import javafx.scene.paint.Color
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BarShapeTest {

    val runs = RunsBuilder()

    @Test
    fun `at left with no left neighbor`() {
        runs.run()
        val lastUpperRight = org.geepawhill.jltk.layout.LogPoint(0, 0)
        val shape = org.geepawhill.jltk.layout.BarShape(runs[0], lastUpperRight, Color.GREEN, Color.GREEN)
        assertThat(shape.x).isEqualTo(0)
        assertThat(shape.width).isEqualTo(1)
        assertThat(shape.height).isEqualTo(1)
    }

    @Test
    fun `at left with taller left neighbor`() {
        runs.run()
        val lastUpperRight = org.geepawhill.jltk.layout.LogPoint(0, 4)
        val shape = org.geepawhill.jltk.layout.BarShape(runs[0], lastUpperRight, Color.GREEN, Color.GREEN)
        assertThat(shape.x).isEqualTo(0)
        assertThat(shape.width).isEqualTo(1)
        assertThat(shape.height).isEqualTo(4)
    }

    @Test
    fun `honors lastUpperRight on x axis`() {
        runs.run()
        val lastUpperRight = org.geepawhill.jltk.layout.LogPoint(5, 4)
        val shape = org.geepawhill.jltk.layout.BarShape(runs[0], lastUpperRight, Color.GREEN, Color.GREEN)
        assertThat(shape.x).isEqualTo(5)
        assertThat(shape.width).isEqualTo(1)
        assertThat(shape.height).isEqualTo(4)
    }

}

