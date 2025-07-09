package com.example.practiceapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.practiceapp.R
import com.example.practiceapp.broadcast.CustomBroadcastReceiver
import com.example.practiceapp.utils.AnimationUtils
import com.example.practiceapp.utils.ThreadManager
import com.google.android.material.card.MaterialCardView

class SettingsFragment : Fragment() {
    
    private lateinit var tvStatus: TextView
    private lateinit var btnSendBroadcast: Button
    private lateinit var btnMultiThread: Button
    private lateinit var btnReset: Button
    private lateinit var cbNotifications: CheckBox
    private lateinit var cbDarkMode: CheckBox
    private lateinit var rgTheme: RadioGroup
    private lateinit var rbBlue: RadioButton
    private lateinit var rbGreen: RadioButton
    private lateinit var rbRed: RadioButton
    private lateinit var cardSettings: MaterialCardView
    
    private val threadManager = ThreadManager.getInstance()
    private var threadCounter = 0
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupListeners()
    }
    
    private fun initViews(view: View) {
        tvStatus = view.findViewById(R.id.tvStatus)
        btnSendBroadcast = view.findViewById(R.id.btnSendBroadcast)
        btnMultiThread = view.findViewById(R.id.btnMultiThread)
        btnReset = view.findViewById(R.id.btnReset)
        cbNotifications = view.findViewById(R.id.cbNotifications)
        cbDarkMode = view.findViewById(R.id.cbDarkMode)
        rgTheme = view.findViewById(R.id.rgTheme)
        rbBlue = view.findViewById(R.id.rbBlue)
        rbGreen = view.findViewById(R.id.rbGreen)
        rbRed = view.findViewById(R.id.rbRed)
        cardSettings = view.findViewById(R.id.cardSettings)
    }
    
    private fun setupListeners() {
        // 发送广播按钮
        btnSendBroadcast.setOnClickListener {
            AnimationUtils.scaleAnimation(btnSendBroadcast)
            sendCustomBroadcast()
        }
        
        // 多线程测试按钮
        btnMultiThread.setOnClickListener {
            AnimationUtils.rotationAnimation(btnMultiThread)
            runMultiThreadTest()
        }
        
        // 重置按钮
        btnReset.setOnClickListener {
            AnimationUtils.bounceAnimation(btnReset)
            resetSettings()
        }
        
        // 复选框监听
        cbNotifications.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "开启" else "关闭"
            updateStatus("通知 $status")
        }
        
        cbDarkMode.setOnCheckedChangeListener { _, isChecked ->
            val mode = if (isChecked) "深色" else "浅色"
            updateStatus("主题: $mode 模式")
            AnimationUtils.alphaAnimation(cardSettings, 1f, 0.8f)
        }
        
        // 单选按钮监听
        rgTheme.setOnCheckedChangeListener { _, checkedId ->
            val color = when (checkedId) {
                R.id.rbBlue -> "蓝色"
                R.id.rbGreen -> "绿色"
                R.id.rbRed -> "红色"
                else -> "默认"
            }
            updateStatus("主题颜色: $color")
        }
    }
    
    private fun sendCustomBroadcast() {
        val intent = Intent(CustomBroadcastReceiver.CUSTOM_ACTION).apply {
            putExtra(CustomBroadcastReceiver.EXTRA_MESSAGE, "来自设置页面的广播消息")
        }
        context?.sendBroadcast(intent)
        updateStatus("广播已发送")
    }
    
    private fun runMultiThreadTest() {
        updateStatus("启动多线程测试...")
        threadCounter = 0
        
        // 启动多个线程
        repeat(5) { index ->
            threadManager.executeInBackgroundWithResult(
                backgroundTask = {
                    Thread.sleep((1000..3000).random().toLong())
                    threadManager.updateDataWithLock(mutableListOf(threadCounter)) { list ->
                        threadCounter++
                    }
                    "线程 $index 完成"
                },
                onResult = { result ->
                    updateStatus("$result (总计: $threadCounter)")
                    if (threadCounter == 5) {
                        Toast.makeText(context, "所有线程已完成", Toast.LENGTH_SHORT).show()
                        AnimationUtils.bounceAnimation(tvStatus)
                    }
                }
            )
        }
    }
    
    private fun resetSettings() {
        cbNotifications.isChecked = false
        cbDarkMode.isChecked = false
        rgTheme.clearCheck()
        updateStatus("设置已重置")
        AnimationUtils.enterAnimation(cardSettings)
    }
    
    private fun updateStatus(message: String) {
        tvStatus.text = message
        AnimationUtils.alphaAnimation(tvStatus, 0.5f, 1f)
    }
}