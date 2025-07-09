package com.example.practiceapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practiceapp.fragment.DashboardFragment
import com.example.practiceapp.fragment.ListFragment
import com.example.practiceapp.fragment.SettingsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    
    private val fragments = listOf(
        ListFragment(),
        DashboardFragment(),
        SettingsFragment()
    )
    
    override fun getItemCount(): Int = fragments.size
    
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}