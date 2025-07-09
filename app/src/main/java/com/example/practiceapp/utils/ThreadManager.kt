package com.example.practiceapp.utils

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class ThreadManager {
    
    private val lock = ReentrantLock()
    private val mainHandler = Handler(Looper.getMainLooper())
    
    companion object {
        @Volatile
        private var INSTANCE: ThreadManager? = null
        
        fun getInstance(): ThreadManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ThreadManager().also { INSTANCE = it }
            }
        }
    }
    
    /**
     * 使用线程锁安全地更新数据
     */
    fun <T> updateDataWithLock(data: MutableList<T>, operation: (MutableList<T>) -> Unit) {
        lock.withLock {
            operation(data)
        }
    }
    
    /**
     * 在后台线程执行任务
     */
    fun executeInBackground(task: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            task()
        }
    }
    
    /**
     * 在后台线程执行任务并在主线程更新UI
     */
    fun <T> executeInBackgroundWithResult(
        backgroundTask: suspend () -> T,
        onResult: (T) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = backgroundTask()
            withContext(Dispatchers.Main) {
                onResult(result)
            }
        }
    }
    
    /**
     * 在主线程执行任务
     */
    fun executeInMainThread(task: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            task()
        } else {
            mainHandler.post(task)
        }
    }
    
    /**
     * 延迟执行任务
     */
    fun executeDelayed(delayMillis: Long, task: () -> Unit) {
        mainHandler.postDelayed(task, delayMillis)
    }
    
    /**
     * 模拟长时间运行的任务
     */
    suspend fun simulateLongRunningTask(durationMillis: Long): String {
        return withContext(Dispatchers.IO) {
            Thread.sleep(durationMillis)
            "任务完成 - 耗时: ${durationMillis}ms"
        }
    }
}