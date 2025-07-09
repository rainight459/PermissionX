package com.example.practiceapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.practiceapp.adapter.ViewPagerAdapter
import com.example.practiceapp.utils.AnimationUtils
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: ViewPagerAdapter
    
    private val tabTitles = listOf("列表", "控制台", "设置")
    private val tabIcons = listOf(
        R.drawable.ic_list,
        R.drawable.ic_dashboard,
        R.drawable.ic_settings
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        initViews()
        setupViewPager()
        setupTabLayout()
    }
    
    private fun initViews() {
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
    }
    
    private fun setupViewPager() {
        adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter
        
        // 设置页面切换监听
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // 为当前选中的 tab 添加动画
                val currentTab = tabLayout.getTabAt(position)
                currentTab?.view?.let { tabView ->
                    AnimationUtils.scaleAnimation(tabView, 1.0f, 1.1f, 200)
                }
            }
        })
        
        // 设置页面切换动画
        viewPager.setPageTransformer { page, position ->
            when {
                position < -1 -> { // 页面完全不可见
                    page.alpha = 0f
                }
                position <= 1 -> { // 页面可见或部分可见
                    page.alpha = 1f
                    page.translationX = 0f
                    page.scaleX = 1f
                    page.scaleY = 1f
                    
                    // 添加淡入淡出效果
                    page.alpha = 1 - kotlin.math.abs(position)
                    
                    // 添加缩放效果
                    val scaleFactor = 0.85f + (1 - kotlin.math.abs(position)) * 0.15f
                    page.scaleX = scaleFactor
                    page.scaleY = scaleFactor
                }
                else -> { // 页面完全不可见
                    page.alpha = 0f
                }
            }
        }
    }
    
    private fun setupTabLayout() {
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
            tab.setIcon(tabIcons[position])
        }.attach()
        
        // 为 TabLayout 添加选择监听
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.view?.let { tabView ->
                    AnimationUtils.bounceAnimation(tabView, 300)
                }
            }
            
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.let { tabView ->
                    AnimationUtils.scaleAnimation(tabView, 1.1f, 1.0f, 200)
                }
            }
            
            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.view?.let { tabView ->
                    AnimationUtils.shakeAnimation(tabView)
                }
            }
        })
    }
    
    override fun onResume() {
        super.onResume()
        // 为整个 TabLayout 添加进入动画
        AnimationUtils.enterAnimation(tabLayout)
    }
}