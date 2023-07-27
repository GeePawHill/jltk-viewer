package org.geepawhill.jltkv.layout

import org.geepawhill.jltkv.parse.Commit
import org.geepawhill.jltkv.ui.ShapeAdder


class CommitShape(val commit: Commit, lastUpperRight: LogPoint) :
    LogShape {
    override val detail = commit.detail

    override val x: Int = lastUpperRight.x
    val width: Int = commit.width
    val height: Int = lastUpperRight.y + 1

    override fun add(adder: ShapeAdder) {
    }
}