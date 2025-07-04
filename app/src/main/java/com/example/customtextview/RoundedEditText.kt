package com.example.customtextview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class RoundedEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    private var cornerRadius: Float = 0f
    private var strokeWidth: Float = 0f
    private var strokeColor: Int = Color.TRANSPARENT
    private var fillColor: Int = Color.TRANSPARENT
    private var focusedStrokeColor: Int = Color.TRANSPARENT
    private var focusedFillColor: Int = Color.TRANSPARENT
    private var focusedStrokeWidth: Float = 0f

    init {
        // 获取自定义属性
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundedEditText)
        
        cornerRadius = typedArray.getDimension(R.styleable.RoundedEditText_cornerRadius, 0f)
        strokeWidth = typedArray.getDimension(R.styleable.RoundedEditText_strokeWidth, 0f)
        strokeColor = typedArray.getColor(R.styleable.RoundedEditText_strokeColor, Color.TRANSPARENT)
        fillColor = typedArray.getColor(R.styleable.RoundedEditText_fillColor, Color.TRANSPARENT)
        focusedStrokeColor = typedArray.getColor(R.styleable.RoundedEditText_focusedStrokeColor, strokeColor)
        focusedFillColor = typedArray.getColor(R.styleable.RoundedEditText_focusedFillColor, fillColor)
        focusedStrokeWidth = typedArray.getDimension(R.styleable.RoundedEditText_focusedStrokeWidth, strokeWidth)
        
        typedArray.recycle()
        
        // 设置背景
        updateBackground()
        
        // 设置焦点改变监听器
        setOnFocusChangeListener { _, hasFocus ->
            updateBackground()
        }
    }

    private fun updateBackground() {
        val drawable = GradientDrawable()
        
        // 设置圆角
        drawable.cornerRadius = cornerRadius
        
        // 根据焦点状态设置颜色和宽度
        val currentFillColor = if (hasFocus() && focusedFillColor != Color.TRANSPARENT) {
            focusedFillColor
        } else {
            fillColor
        }
        
        val currentStrokeColor = if (hasFocus() && focusedStrokeColor != Color.TRANSPARENT) {
            focusedStrokeColor
        } else {
            strokeColor
        }
        
        val currentStrokeWidth = if (hasFocus() && focusedStrokeWidth > 0) {
            focusedStrokeWidth
        } else {
            strokeWidth
        }
        
        // 设置背景色
        if (currentFillColor != Color.TRANSPARENT) {
            drawable.setColor(currentFillColor)
        }
        
        // 设置边框
        if (currentStrokeWidth > 0 && currentStrokeColor != Color.TRANSPARENT) {
            drawable.setStroke(currentStrokeWidth.toInt(), currentStrokeColor)
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
        updateBackground()
    }

    fun setFocusedColors(strokeColor: Int, fillColor: Int) {
        focusedStrokeColor = strokeColor
        focusedFillColor = fillColor
        updateBackground()
    }
    
    fun setFocusedStrokeWidth(width: Float) {
        focusedStrokeWidth = width
        updateBackground()
    }
}