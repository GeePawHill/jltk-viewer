package org.geepawhill.jltkv.ui

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import javafx.scene.shape.Path
import javafx.scene.shape.Rectangle
import javafx.scene.text.Font
import org.geepawhill.jltkv.layout.BarShape
import org.geepawhill.jltkv.layout.ColumnDetail
import org.geepawhill.jltkv.layout.CommitShape
import org.geepawhill.jltkv.layout.TestShape
import org.geepawhill.jltkv.parse.TestResult
import tornadofx.closepath
import tornadofx.lineTo
import tornadofx.moveTo
import tornadofx.tooltip

class ShapeAdder(
    val model: org.geepawhill.jltkv.ViewerModel,
    val totalHeight: Double,
    val destination: MutableList<Node>
) {

    fun makeCommitShape(
        shape: CommitShape,
        detail: ColumnDetail
    ) {
        val left = GraphicViewer.SCALE_FACTOR * shape.x
        val farRight = GraphicViewer.SCALE_FACTOR * (shape.x + shape.width)
        val nearRight = farRight - GraphicViewer.SCALE_FACTOR
        val bottom = totalHeight
        val top = totalHeight - (shape.height * GraphicViewer.SCALE_FACTOR)
        if (shape.commit.isEmpty()) {
            destination.add(makeEmptyCommit(left, bottom, top, nearRight, farRight, detail))
        } else {
            val path = makeNonEmptyCommit(left, bottom, nearRight, top, farRight, detail)
            destination.add(path)
        }
    }

    private fun makeNonEmptyCommit(
        left: Double,
        bottom: Double,
        nearRight: Double,
        top: Double,
        farRight: Double,
        detail: ColumnDetail
    ): Path {
        val path = Path().apply {
            moveTo(left, bottom)
            lineTo(left, bottom - GraphicViewer.SCALE_FACTOR)
            lineTo(nearRight, bottom - GraphicViewer.SCALE_FACTOR)
            lineTo(nearRight, top)
            lineTo(farRight, top)
            lineTo(farRight, bottom)
            closepath()
            fill = Color.ORCHID
            stroke = Color.BLACK
            tooltip {
                font = Font.font(20.0)
                text = "commit"
            }
            onMouseClicked = EventHandler { model.columnDetail.set(detail) }
        }
        return path
    }

    private fun makeEmptyCommit(
        left: Double,
        bottom: Double,
        top: Double,
        nearRight: Double,
        farRight: Double,
        detail: ColumnDetail
    ): Path {
        val path = Path().apply {
            moveTo(left, bottom)
            lineTo(left, top)
            lineTo(nearRight, top)
            lineTo(farRight, top)
            lineTo(farRight, bottom)
            closepath()
            fill = Color.ORCHID
            stroke = Color.BLACK
            tooltip {
                font = Font.font(20.0)
                text = "commit"
            }
            onMouseClicked = EventHandler { model.columnDetail.set(detail) }
        }
        return path
    }

    fun makeTest(test: TestShape, detail: ColumnDetail) {
        val left = GraphicViewer.SCALE_FACTOR * test.x
        val width = GraphicViewer.SCALE_FACTOR
        val height = GraphicViewer.SCALE_FACTOR
        val top = totalHeight - ((test.y + 1) * GraphicViewer.SCALE_FACTOR)
        val rectangle = Rectangle(left, top, width, height).apply {
            fill = makeTestFill(test.result)
            stroke = Color.BLACK
            tooltip {
                font = Font.font(20.0)
                text = test.result.name
            }
            onMouseClicked = EventHandler { model.columnDetail.set(detail) }
        }
        destination.add(rectangle)
        if (!test.result.isNew) return
        val circle = Circle(
            left + GraphicViewer.SCALE_FACTOR / 2.0,
            top + GraphicViewer.SCALE_FACTOR / 2.0,
            GraphicViewer.SCALE_FACTOR / 3.0
        ).apply {
            fill = Color.WHITE
            tooltip {
                font = Font.font(20.0)
                text = test.result.name
            }
            onMouseClicked = EventHandler { model.columnDetail.set(detail) }
        }
        destination.add(circle)
    }

    private fun makeTestFill(test: TestResult): Paint {
        return test.color
    }

    fun makeBar(bar: BarShape, detail: ColumnDetail) {
        val left = GraphicViewer.SCALE_FACTOR * bar.x
        val width = bar.width * GraphicViewer.SCALE_FACTOR
        val height = bar.height * GraphicViewer.SCALE_FACTOR
        val top = totalHeight - ((1 + bar.height) * GraphicViewer.SCALE_FACTOR)
        val rectangle = Rectangle(left, top, width, height).apply {
            fill = bar.fill
            stroke = bar.border
            onMouseClicked = EventHandler { model.columnDetail.set(detail) }
        }
        destination.add(rectangle)
    }
}
