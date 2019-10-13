package com.seljabali.practiceproblems.ui.htree

import kotlin.math.sqrt

class HTree private constructor(midPoint: Point, length: Double) {

    private val leftLine: Line
    private val rightLine: Line
    private val middleLine: Line
    private val height: Double
    private var topLeftTree: HTree? = null
    private var bottomLeftTree: HTree? = null
    private var topRightTree: HTree? = null
    private var bottomRightTree: HTree? = null

    constructor(midPoint: Point, length: Double, depth: Int) : this(midPoint, length) {
        populateTree(this, 1, depth)
    }

    init {
        val halfWidth = length / 2
        height = length / sqrt(2.0)
        middleLine = Line(
            Point(midPoint.x - halfWidth, midPoint.y),
            Point(midPoint.x + halfWidth, midPoint.y)
        )
        leftLine = Line(
            Point(middleLine.fromPoint.x, middleLine.fromPoint.y - height / 2),
            Point(middleLine.fromPoint.x, middleLine.fromPoint.y + height / 2)
        )
        rightLine = Line(
            Point(middleLine.toPoint.x, middleLine.toPoint.y - height / 2),
            Point(middleLine.toPoint.x, middleLine.toPoint.y + height / 2)
        )
    }

    private fun populateTree(currentHTree: HTree?, currentDepth: Int, maxDepth: Int) {
        if (currentDepth >= maxDepth) {
            return
        }
        currentHTree!!.topLeftTree = HTree(
            Point(currentHTree.leftLine.toPoint.x, currentHTree.leftLine.toPoint.y),
            currentHTree.height
        )
        currentHTree.bottomLeftTree = HTree(
            Point(currentHTree.leftLine.toPoint.x, currentHTree.leftLine.fromPoint.y),
            currentHTree.height
        )
        currentHTree.topRightTree = HTree(
            Point(currentHTree.rightLine.toPoint.x, currentHTree.rightLine.toPoint.y),
            currentHTree.height
        )
        currentHTree.bottomRightTree = HTree(
            Point(currentHTree.rightLine.toPoint.x, currentHTree.rightLine.fromPoint.y),
            currentHTree.height
        )

        populateTree(currentHTree.topLeftTree, currentDepth + 1, maxDepth)
        populateTree(currentHTree.bottomLeftTree, currentDepth + 1, maxDepth)
        populateTree(currentHTree.topRightTree, currentDepth + 1, maxDepth)
        populateTree(currentHTree.bottomRightTree, currentDepth + 1, maxDepth)
    }

    companion object {

        fun drawTree(hTree: HTree?, lineDrawer: LineDrawer?) {
            if (hTree == null || lineDrawer == null) {
                return
            }
            drawLine(hTree.middleLine, lineDrawer)
            drawLine(hTree.leftLine, lineDrawer)
            drawLine(hTree.rightLine, lineDrawer)

            drawTree(hTree.bottomLeftTree, lineDrawer)
            drawTree(hTree.bottomRightTree, lineDrawer)
            drawTree(hTree.topLeftTree, lineDrawer)
            drawTree(hTree.topRightTree, lineDrawer)
        }

        private fun drawLine(line: Line, lineDrawer: LineDrawer) {
            drawLine(line.fromPoint, line.toPoint, lineDrawer)
        }

        private fun drawLine(pointA: Point, pointB: Point, lineDrawer: LineDrawer) {
            lineDrawer.drawLine(pointA, pointB)
        }
    }
}
