package com.example.customtextview

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // 设置可点击按钮的点击事件
        val clickableButton = findViewById<RoundedTextView>(R.id.clickableButton)
        clickableButton.setOnClickListener {
            Toast.makeText(this, "按钮被点击了！", Toast.LENGTH_SHORT).show()
            
            // 演示动态更改样式
            clickableButton.setGradientColors(
                Color.parseColor("#E91E63"),
                Color.parseColor("#9C27B0"),
                GradientDrawable.Orientation.LEFT_RIGHT
            )
            clickableButton.text = "样式已更新！"
        }
        
        // 演示RoundedEditText的功能
        setupEditTextDemo()
    }
    
    private fun setupEditTextDemo() {
        // 可以在这里添加输入框的交互逻辑
        // 例如：表单验证、实时搜索等
        
        // 如果有特定的ID，可以这样设置监听器
        // val searchEditText = findViewById<RoundedEditText>(R.id.search_edit_text)
        // searchEditText.addTextChangedListener(object : TextWatcher {
        //     override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        //     override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //         // 实时搜索逻辑
        //     }
        //     override fun afterTextChanged(s: Editable?) {}
        // })
    }
}