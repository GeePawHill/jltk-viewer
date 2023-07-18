package org.geepawhill.jltkv.ui

import javafx.scene.Node
import org.geepawhill.jltkv.layout.ColumnDetail
import org.geepawhill.jltkv.layout.CommitShape

class ShapeAdder(
    val model: org.geepawhill.jltkv.ViewerModel,
    val totalHeight: Double,
    val destination: MutableList<Node>
) {

    fun makeCommitShape(
        shape: CommitShape,
        detail: ColumnDetail
    ) {
    }

}
