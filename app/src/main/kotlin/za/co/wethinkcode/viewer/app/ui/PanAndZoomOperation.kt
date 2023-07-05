package za.co.wethinkcode.viewer.app.ui

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.transform.Scale

class PanAndZoomOperation(val node: Node) {
    var anchorX = 0.0
    var anchorY = 0.0

    fun drag(start: MouseEvent) {
        node.onMouseDragged = EventHandler { event -> dragging(event) }
        node.onMouseReleased = EventHandler { _ -> drop() }
        anchorX = start.sceneX
        anchorY = start.sceneY
    }

    fun dragging(event: MouseEvent) {
        node.translateX = event.sceneX - anchorX
        node.translateY = event.sceneY - anchorY
    }

    fun drop() {
        node.layoutX += node.translateX
        node.layoutY += node.translateY
        node.translateX = 0.0
        node.translateY = 0.0
        node.onMouseDragged = EventHandler { }
        node.onMouseReleased = EventHandler { }
    }

    init {
        node.onMousePressed = EventHandler { event -> this.drag(event) }
        node.onScroll = EventHandler { event ->
            val scale = Scale()
            if (event.deltaY > 0) {
                scale.x *= 1.5
                scale.y *= 1.5
            } else {
                scale.x *= 0.66
                scale.y *= 0.66
            }
            val pivot = node.screenToLocal(event.sceneX, event.sceneY)
            scale.pivotX = pivot.x
            scale.pivotY = pivot.y
            node.transforms.add(scale)
        }
    }
}