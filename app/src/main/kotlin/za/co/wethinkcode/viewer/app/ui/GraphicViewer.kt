package za.co.wethinkcode.viewer.app.ui

import tornadofx.*
import za.co.wethinkcode.viewer.app.ViewerModel


class GraphicViewer(val model: ViewerModel) : Fragment() {
    val shapes = group {
        PanAndZoomOperation(this)
    }

    override val root = splitpane {
        pane {
            this += shapes
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
