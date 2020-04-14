package com.example.movingcircle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Shader
import android.hardware.SensorEvent
import android.util.AttributeSet
import android.view.View


class MovingCircleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val radius: Float = 30F
    private var x = 0
    private var y = 0
    private var viewWidth = 0
    private var viewHeight = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h
    }

    fun drawOnSensorEvent(event: SensorEvent) {
        x -= event.values[0].toInt()
        y += event.values[1].toInt()

        if (y <= 0 + radius) {
            y = 0 + radius.toInt()
        }
        if (y >= viewHeight - radius) {
            y = viewHeight - radius.toInt()
        }
        if (x <= 0 + radius) {
            x = 0 + radius.toInt()
        }
        if (x >= viewWidth - radius) {
            x = viewWidth - radius.toInt()
        }
    }

    override fun onDraw(canvas: Canvas) {
        var paint = Paint();
        paint.color = Color.rgb( 50, 55, 195);
        canvas.drawRGB( 240, 240, 240)
        canvas.drawCircle(x.toFloat(), y.toFloat(), radius, paint)
        invalidate()
    }

}