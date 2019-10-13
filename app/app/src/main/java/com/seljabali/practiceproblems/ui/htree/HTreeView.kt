package com.seljabali.practiceproblems.ui.htree

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.ViewTreeObserver

class HTreeView(context: Context, attrs: AttributeSet) : View(context, attrs), LineDrawer {

    companion object {
        private val LINE_COLOR = "#E74300"
        private val LINE_WIDTH = 5
        private val LINE_LENGTH = 250
        private val DEFAULT_DEPTH = 1
    }

    private val drawPaint: Paint = Paint()
    private var canvas: Canvas? = null
    private var tree: HTree? = null
    private var centerScreen: Point? = null

    init {
        drawPaint.color = Color.parseColor(LINE_COLOR)
        drawPaint.isAntiAlias = true
        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeWidth = LINE_WIDTH.toFloat()
        setOnMeasureCallback()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.canvas = canvas
        if (tree == null) {
            tree = HTree(centerScreen!!, LINE_LENGTH.toDouble(), DEFAULT_DEPTH)
        }
        HTree.drawTree(tree, this)
    }

    override fun drawLine(pointA: Point, pointB: Point) {
        canvas!!.drawLine(
            pointA.x.toFloat(),
            pointA.y.toFloat(),
            pointB.x.toFloat(),
            pointB.y.toFloat(),
            drawPaint
        )
        invalidate()
    }

    fun setDepth(depth: Int) {
        tree = HTree(centerScreen!!, LINE_LENGTH.toDouble(), depth)
        invalidate()
    }

    private fun setOnMeasureCallback() {
        val vto = viewTreeObserver
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                removeOnGlobalLayoutListener(this)
                val size = measuredWidth.toDouble()
                centerScreen = Point(size / 2, size / 2)
            }
        })
    }

    private fun removeOnGlobalLayoutListener(listener: ViewTreeObserver.OnGlobalLayoutListener) {
        viewTreeObserver.removeOnGlobalLayoutListener(listener)
    }

}