package com.example.practiceapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.practiceapp.R
import com.example.practiceapp.utils.AnimationUtils
import com.example.practiceapp.utils.ThreadManager
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial

class DashboardFragment : Fragment() {
    
    private lateinit var etInput: EditText
    private lateinit var btnSubmit: Button
    private lateinit var btnAnimate: Button
    private lateinit var btnTask: Button
    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var seekBar: SeekBar
    private lateinit var switchMaterial: SwitchMaterial
    private lateinit var slider: Slider
    
    private val threadManager = ThreadManager.getInstance()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupListeners()
    }
    
    private fun initViews(view: View) {
        etInput = view.findViewById(R.id.etInput)
        btnSubmit = view.findViewById(R.id.btnSubmit)
        btnAnimate = view.findViewById(R.id.btnAnimate)
        btnTask = view.findViewById(R.id.btnTask)
        tvResult = view.findViewById(R.id.tvResult)
        progressBar = view.findViewById(R.id.progressBar)
        seekBar = view.findViewById(R.id.seekBar)
        switchMaterial = view.findViewById(R.id.switchMaterial)
        slider = view.findViewById(R.id.slider)
    }
    
    private fun setupListeners() {
        // 提交按钮
        btnSubmit.setOnClickListener {
            val input = etInput.text.toString()
            if (input.isNotEmpty()) {
                AnimationUtils.scaleAnimation(btnSubmit)
                tvResult.text = "输入内容: $input"
                AnimationUtils.alphaAnimation(tvResult, 0f, 1f)
            } else {
                AnimationUtils.shakeAnimation(etInput)
                Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show()
            }
        }
        
        // 动画按钮
        btnAnimate.setOnClickListener {
            showRandomAnimation()
        }
        
        // 任务按钮
        btnTask.setOnClickListener {
            executeBackgroundTask()
        }
        
        // SeekBar 监听
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                progressBar.progress = progress
                tvResult.text = "进度: $progress%"
            }
            
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        
        // Switch 监听
        switchMaterial.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AnimationUtils.alphaAnimation(tvResult, 1f, 0.5f)
                tvResult.text = "开关已打开"
            } else {
                AnimationUtils.alphaAnimation(tvResult, 0.5f, 1f)
                tvResult.text = "开关已关闭"
            }
        }
        
        // Slider 监听
        slider.addOnChangeListener { _, value, _ ->
            val percentage = (value * 100).toInt()
            tvResult.text = "滑动值: $percentage%"
        }
    }
    
    private fun showRandomAnimation() {
        val animations = listOf(
            { AnimationUtils.bounceAnimation(btnAnimate) },
            { AnimationUtils.rotationAnimation(btnAnimate) },
            { AnimationUtils.scaleAnimation(btnAnimate, 1.0f, 1.2f) },
            { AnimationUtils.shakeAnimation(btnAnimate) }
        )
        
        val randomAnimation = animations.random()
        randomAnimation.invoke()
    }
    
    private fun executeBackgroundTask() {
        // 显示加载状态
        progressBar.visibility = View.VISIBLE
        btnTask.isEnabled = false
        tvResult.text = "正在执行后台任务..."
        
        threadManager.executeInBackgroundWithResult(
            backgroundTask = {
                // 模拟长时间运行的任务
                threadManager.simulateLongRunningTask(3000)
            },
            onResult = { result ->
                progressBar.visibility = View.GONE
                btnTask.isEnabled = true
                tvResult.text = result
                AnimationUtils.bounceAnimation(tvResult)
            }
        )
    }
}