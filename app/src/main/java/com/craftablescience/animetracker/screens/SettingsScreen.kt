package com.craftablescience.animetracker.screens

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.craftablescience.animetracker.R
import com.craftablescience.animetracker.fragments.settings.*

enum class SettingsScreen(@IdRes override val menuItemId: Int,
                          @DrawableRes override val menuItemIconId: Int,
                          @StringRes override val titleStringId: Int,
                          override val fragment: Fragment) : ActivityScreen {
    ACCOUNT(
        R.id.settings_bottom_navigation_item_account,
        R.drawable.ic_currentlywatching_white,
        R.string.settings_account,
        AccountFragment()
    ),
    UI(
        R.id.settings_bottom_navigation_item_ui,
        R.drawable.ic_completed_white,
        R.string.settings_ui,
        UIFragment()
    ),
    ABOUT(
        R.id.settings_bottom_navigation_item_about,
        R.drawable.ic_onhold_white,
        R.string.settings_about,
        AboutFragment()
    );
}

fun getSettingsScreenForMenuItem(menuItemId: Int): SettingsScreen? {
    for (settingsScreen in SettingsScreen.values()) {
        if (settingsScreen.menuItemId == menuItemId) {
            return settingsScreen
        }
    }
    return null
}