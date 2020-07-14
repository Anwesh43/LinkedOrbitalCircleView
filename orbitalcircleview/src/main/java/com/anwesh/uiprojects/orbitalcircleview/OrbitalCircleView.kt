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
