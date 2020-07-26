package com.craftablescience.animetracker.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.craftablescience.animetracker.screens.ActivityScreen

class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val screens = arrayListOf<ActivityScreen>()

    fun setItems(screens: List<ActivityScreen>) {
        this.screens.apply {
            clear()
            addAll(screens)
            notifyDataSetChanged()
        }
    }

    fun getItems(): List<ActivityScreen> {
        return screens
    }

    override fun getItem(position: Int): Fragment {
        return screens[position].fragment
    }

    override fun getCount(): Int {
        return screens.size
    }
}