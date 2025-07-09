package com.example.practiceapp.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

object AnimationUtils {
    
    /**
     * 缩放动画
     */
    fun scaleAnimation(view: View, fromScale: Float = 1.0f, toScale: Float = 1.1f, duration: Long = 300) {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", fromScale, toScale)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", fromScale, toScale)
        
        AnimatorSet().apply {
            playTogether(scaleX, scaleY)
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }
    
    /**
     * 透明度动画
     */
    fun alphaAnimation(view: View, fromAlpha: Float = 0f, toAlpha: Float = 1f, duration: Long = 300) {
        ObjectAnimator.ofFloat(view, "alpha", fromAlpha, toAlpha).apply {
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }
    
    /**
     * 旋转动画
     */
    fun rotationAnimation(view: View, fromRotation: Float = 0f, toRotation: Float = 360f, duration: Long = 500) {
        ObjectAnimator.ofFloat(view, "rotation", fromRotation, toRotation).apply {
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }
    
    /**
     * 平移动画
     */
    fun translationAnimation(view: View, fromX: Float = 0f, toX: Float = 100f, duration: Long = 300) {
        ObjectAnimator.ofFloat(view, "translationX", fromX, toX).apply {
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }
    
    /**
     * 弹跳动画
     */
    fun bounceAnimation(view: View, duration: Long = 600) {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.2f, 1.0f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.2f, 1.0f)
        
        AnimatorSet().apply {
            playTogether(scaleX, scaleY)
            this.duration = duration
            interpolator = BounceInterpolator()
            start()
        }
    }
    
    /**
     * 震动动画
     */
    fun shakeAnimation(view: View, duration: Long = 300) {
        ObjectAnimator.ofFloat(view, "translationX", 0f, 25f, -25f, 25f, -25f, 15f, -15f, 6f, -6f, 0f).apply {
            this.duration = duration
            start()
        }
    }
    
    /**
     * 弹性动画
     */
    fun springAnimation(view: View, finalPosition: Float) {
        val springForce = SpringForce(finalPosition).apply {
            stiffness = SpringForce.STIFFNESS_MEDIUM
            dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
        }
        
        SpringAnimation(view, DynamicAnimation.TRANSLATION_Y).apply {
            spring = springForce
            start()
        }
    }
    
    /**
     * 组合动画 - 进入效果
     */
    fun enterAnimation(view: View, duration: Long = 600) {
        view.apply {
            alpha = 0f
            scaleX = 0.8f
            scaleY = 0.8f
            translationY = 100f
        }
        
        val alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1f)
        val translationY = ObjectAnimator.ofFloat(view, "translationY", 100f, 0f)
        
        AnimatorSet().apply {
            playTogether(alpha, scaleX, scaleY, translationY)
            this.duration = duration
            interpolator = OvershootInterpolator()
            start()
        }
    }
    
    /**
     * 组合动画 - 退出效果
     */
    fun exitAnimation(view: View, duration: Long = 300, onComplete: () -> Unit = {}) {
        val alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.8f)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.8f)
        val translationY = ObjectAnimator.ofFloat(view, "translationY", 0f, -100f)
        
        AnimatorSet().apply {
            playTogether(alpha, scaleX, scaleY, translationY)
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator()
            addListener(object : android.animation.AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    onComplete()
                }
            })
            start()
        }
    }
}