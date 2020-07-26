package com.craftablescience.animetracker.screens

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.craftablescience.animetracker.R

import com.craftablescience.animetracker.fragments.main.*

enum class MainScreen(@IdRes override val menuItemId: Int,
                      @DrawableRes override val menuItemIconId: Int,
                      @StringRes override val titleStringId: Int,
                      override val fragment: Fragment) : ActivityScreen {
    CURRENTLYWATCHING(
        R.id.main_bottom_navigation_item_currentlywatching,
        R.drawable.ic_currentlywatching_white,
        R.string.main_currentlywatching,
        CurrentlyWatchingFragment()
    ),
    COMPLETED(
        R.id.main_bottom_navigation_item_completed,
        R.drawable.ic_completed_white,
        R.string.main_completed,
        CompletedFragment()
    ),
    ONHOLD(
        R.id.main_bottom_navigation_item_onhold,
        R.drawable.ic_onhold_white,
        R.string.main_onhold,
        OnHoldFragment()
    ),
    DROPPED(
        R.id.main_bottom_navigation_item_dropped,
        R.drawable.ic_dropped_white,
        R.string.main_dropped,
        DroppedFragment()
    ),
    PLANNINGTOWATCH(
        R.id.main_bottom_navigation_item_planningtowatch,
        R.drawable.ic_planningtowatch_white,
        R.string.main_planningtowatch,
        PlanningToWatchFragment()
    );
}

fun getMainScreenForMenuItem(menuItemId: Int): MainScreen? {
    for (mainScreen in MainScreen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}