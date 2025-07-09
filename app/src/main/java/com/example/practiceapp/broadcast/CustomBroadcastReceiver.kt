package com.example.practiceapp.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class CustomBroadcastReceiver : BroadcastReceiver() {
    
    companion object {
        const val CUSTOM_ACTION = "com.example.practiceapp.CUSTOM_ACTION"
        const val EXTRA_MESSAGE = "extra_message"
        const val TAG = "CustomBroadcastReceiver"
    }
    
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            CUSTOM_ACTION -> {
                val message = intent.getStringExtra(EXTRA_MESSAGE) ?: "默认消息"
                Log.d(TAG, "收到自定义广播: $message")
                context?.let {
                    Toast.makeText(it, "广播消息: $message", Toast.LENGTH_SHORT).show()
                }
            }
            
            Intent.ACTION_BOOT_COMPLETED -> {
                Log.d(TAG, "系统启动完成")
                context?.let {
                    Toast.makeText(it, "系统启动完成", Toast.LENGTH_SHORT).show()
                }
            }
            
            else -> {
                Log.d(TAG, "收到未知广播: ${intent?.action}")
            }
        }
    }
}