package org.geepawhill.jltkv.ui

import tornadofx.*


class GraphicViewer(val model: org.geepawhill.jltkv.ViewerModel) : Fragment() {
    lateinit var panAndZoomOperation: PanAndZoomOperation
    val shapes = group {
        panAndZoomOperation = PanAndZoomOperation(this)
    }

    override val root = splitpane {
        pane {
            this += shapes
            button("O") {
                action {
                    panAndZoomOperation.reset()
                }
            }
        }
        this += ColumnDetailView(model).root
    }

    init {
        model.logShapes.addListener { _, _, _ -> shapesChanged() }
    }

    private fun shapesChanged() {
        shapes.children.clear()
        val paths = model.logShapes.value
        val totalHeight = SCALE_FACTOR * paths.height
        val totalWidth = paths.width * SCALE_FACTOR
        val adder = ShapeAdder(model, totalHeight, shapes.children)
        for (shape in paths.shapes) shape.add(adder)
    }

    companion object {
        const val SCALE_FACTOR = 30.0
    }
}
