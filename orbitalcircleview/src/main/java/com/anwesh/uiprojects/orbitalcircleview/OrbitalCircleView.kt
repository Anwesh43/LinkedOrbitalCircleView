package com.anwesh.uiprojects.orbitalcircleview

/**
 * Created by anweshmishra on 15/07/20.
 */

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val colors : Array<String> = arrayOf("#F44336", "#3F51B5", "#4CAF50", "#2196F3", "#FF9800")
val orbits : Int = 3
val rFactor : Float = 15f
val strokeFactor : Float = 90f
val delay : Long = 20
val backColor : Int = Color.parseColor("#BDBDBD")
val scGap : Float = 0.02f / orbits

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawOrbitalCircle(i : Int, scale : Float, w : Float, h : Float, paint : Paint) {
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, 2)
    val sf2 : Float = sf.divideScale(1, 2)
    val sf2i : Float = sf2.divideScale(i, orbits)
    val concR = Math.min(w, h) / orbits
    val r : Float = Math.min(w, h) / rFactor
    val deg : Float = 90f / orbits
    val x : Float = sf2i * concR * i
    if (i == 0) {
        paint.style = Paint.Style.FILL
        drawCircle(0f, 0f, r * sf1, paint)
    }
    save()
    rotate(deg * i)
    paint.style = Paint.Style.FILL
    drawCircle(x, 0f, r * sf1, paint)
    restore()
    paint.style = Paint.Style.STROKE
    drawCircle(0f, 0f, x, paint)
}

fun Canvas.drawOrbitalCircles(scale : Float, w : Float, h : Float, paint : Paint) {
    save()
    translate(w / 2, h / 2)
    for (j in 0..(orbits - 1)) {
        drawOrbitalCircle(j, scale, w, h, paint)
    }
    restore()
}

fun Canvas.drawOCNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = Color.parseColor(colors[i])
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    drawOrbitalCircles(scale, w, h, paint)
}
