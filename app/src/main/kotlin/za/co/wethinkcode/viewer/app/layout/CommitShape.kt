package za.co.wethinkcode.viewer.app.layout

import za.co.wethinkcode.viewer.app.parse.Commit
import za.co.wethinkcode.viewer.app.ui.ShapeAdder

class CommitShape(val commit: Commit, lastUpperRight: LogPoint) : LogShape {
    override val detail = commit.detail

    override val x: Int = lastUpperRight.x
    val width: Int = commit.width
    val height: Int = lastUpperRight.y + 1

    override fun add(adder: ShapeAdder) {
        adder.makeCommitShape(this, ColumnDetail(detail))
    }
}