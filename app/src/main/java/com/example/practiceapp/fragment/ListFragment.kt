package com.example.practiceapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceapp.R
import com.example.practiceapp.adapter.ItemAdapter
import com.example.practiceapp.broadcast.CustomBroadcastReceiver
import com.example.practiceapp.data.Item
import com.example.practiceapp.utils.AnimationUtils
import com.example.practiceapp.utils.ThreadManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter
    private lateinit var fabAdd: FloatingActionButton
    private val threadManager = ThreadManager.getInstance()
    private val items = mutableListOf<Item>()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupRecyclerView()
        setupFab()
        loadInitialData()
    }
    
    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        fabAdd = view.findViewById(R.id.fabAdd)
    }
    
    private fun setupRecyclerView() {
        adapter = ItemAdapter(
            items = items,
            onItemClick = { item ->
                Toast.makeText(context, "点击了: ${item.title}", Toast.LENGTH_SHORT).show()
                // 发送广播
                sendBroadcast(item)
            },
            onItemDelete = { item ->
                removeItem(item)
            }
        )
        
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
    
    private fun setupFab() {
        fabAdd.setOnClickListener {
            AnimationUtils.rotationAnimation(fabAdd, 0f, 360f, 500)
            addNewItem()
        }
    }
    
    private fun loadInitialData() {
        threadManager.executeInBackgroundWithResult(
            backgroundTask = {
                // 模拟从网络或数据库加载数据
                threadManager.simulateLongRunningTask(1000)
                listOf(
                    Item(title = "任务 1", description = "这是第一个任务"),
                    Item(title = "任务 2", description = "这是第二个任务"),
                    Item(title = "任务 3", description = "这是第三个任务")
                )
            },
            onResult = { loadedItems ->
                threadManager.updateDataWithLock(items) { list ->
                    list.addAll(loadedItems)
                }
                adapter.notifyDataSetChanged()
            }
        )
    }
    
    private fun addNewItem() {
        val newItem = Item(
            title = "新任务 ${items.size + 1}",
            description = "这是一个新创建的任务"
        )
        
        threadManager.updateDataWithLock(items) { list ->
            list.add(newItem)
        }
        
        adapter.addItem(newItem)
        recyclerView.smoothScrollToPosition(items.size - 1)
    }
    
    private fun removeItem(item: Item) {
        threadManager.updateDataWithLock(items) { list ->
            list.remove(item)
        }
        adapter.removeItem(item)
        Toast.makeText(context, "已删除: ${item.title}", Toast.LENGTH_SHORT).show()
    }
    
    private fun sendBroadcast(item: Item) {
        val intent = Intent(CustomBroadcastReceiver.CUSTOM_ACTION).apply {
            putExtra(CustomBroadcastReceiver.EXTRA_MESSAGE, "点击了项目: ${item.title}")
        }
        context?.sendBroadcast(intent)
    }
}