package org.geepawhill.jltk.ui

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.transform.Scale

class PanAndZoomOperation(val node: Node, val zoomFactor: Double = 1.25) {
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
                scale.x *= zoomFactor
                scale.y *= zoomFactor
            } else {
                scale.x *= 1.0 / zoomFactor
                scale.y *= 1.0 / zoomFactor
            }
            val pivot = node.screenToLocal(event.sceneX, event.sceneY)
            scale.pivotX = pivot.x
            scale.pivotY = pivot.y
            node.transforms.add(scale)
        }
    }

}