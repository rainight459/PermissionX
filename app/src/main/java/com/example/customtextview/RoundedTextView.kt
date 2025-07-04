package com.example.customtextview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat

class RoundedTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var cornerRadius: Float = 0f
    private var strokeWidth: Float = 0f
    private var strokeColor: Int = Color.TRANSPARENT
    private var fillColor: Int = Color.TRANSPARENT
    private var gradientStartColor: Int = Color.TRANSPARENT
    private var gradientEndColor: Int = Color.TRANSPARENT
    private var gradientOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT

    init {
        // 获取自定义属性
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedTextView)
        
        cornerRadius = typedArray.getDimension(R.styleable.RoundedTextView_cornerRadius, 0f)
        strokeWidth = typedArray.getDimension(R.styleable.RoundedTextView_strokeWidth, 0f)
        strokeColor = typedArray.getColor(R.styleable.RoundedTextView_strokeColor, Color.TRANSPARENT)
        fillColor = typedArray.getColor(R.styleable.RoundedTextView_fillColor, Color.TRANSPARENT)
        gradientStartColor = typedArray.getColor(R.styleable.RoundedTextView_gradientStartColor, Color.TRANSPARENT)
        gradientEndColor = typedArray.getColor(R.styleable.RoundedTextView_gradientEndColor, Color.TRANSPARENT)
        
        val orientationIndex = typedArray.getInt(R.styleable.RoundedTextView_gradientOrientation, 0)
        gradientOrientation = when (orientationIndex) {
            0 -> GradientDrawable.Orientation.LEFT_RIGHT
            1 -> GradientDrawable.Orientation.TOP_BOTTOM
            2 -> GradientDrawable.Orientation.TR_BL
            3 -> GradientDrawable.Orientation.BR_TL
            else -> GradientDrawable.Orientation.LEFT_RIGHT
        }
        
        typedArray.recycle()
        
        // 设置背景
        updateBackground()
    }

    private fun updateBackground() {
        val drawable = GradientDrawable()
        
        // 设置圆角
        drawable.cornerRadius = cornerRadius
        
        // 设置渐变或纯色背景
        if (gradientStartColor != Color.TRANSPARENT && gradientEndColor != Color.TRANSPARENT) {
            drawable.colors = intArrayOf(gradientStartColor, gradientEndColor)
            drawable.orientation = gradientOrientation
        } else if (fillColor != Color.TRANSPARENT) {
            drawable.setColor(fillColor)
        }
        
        // 设置边框
        if (strokeWidth > 0 && strokeColor != Color.TRANSPARENT) {
            drawable.setStroke(strokeWidth.toInt(), strokeColor)
        }
        
        background = drawable
    }

    // 公共方法用于动态更改属性
    fun setCornerRadius(radius: Float) {
        cornerRadius = radius
        updateBackground()
    }

    fun setStrokeWidth(width: Float) {
        strokeWidth = width
        updateBackground()
    }

    fun setStrokeColor(color: Int) {
        strokeColor = color
        updateBackground()
    }

    fun setFillColor(color: Int) {
        fillColor = color
        gradientStartColor = Color.TRANSPARENT
        gradientEndColor = Color.TRANSPARENT
        updateBackground()
    }

    fun setGradientColors(startColor: Int, endColor: Int, orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT) {
        gradientStartColor = startColor
        gradientEndColor = endColor
        gradientOrientation = orientation
        fillColor = Color.TRANSPARENT
        updateBackground()
    }
}