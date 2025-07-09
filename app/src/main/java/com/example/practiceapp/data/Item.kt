package com.example.practiceapp.data

import java.util.concurrent.atomic.AtomicInteger

data class Item(
    val id: Int = idGenerator.incrementAndGet(),
    val title: String,
    val description: String,
    val imageUrl: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val isCompleted: Boolean = false
) {
    companion object {
        private val idGenerator = AtomicInteger(0)
    }
}