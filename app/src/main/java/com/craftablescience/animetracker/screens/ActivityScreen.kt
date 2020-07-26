package com.craftablescience.animetracker.screens

import androidx.fragment.app.Fragment

interface ActivityScreen {
    val menuItemId : Int
    val menuItemIconId : Int
    val titleStringId : Int
    val fragment : Fragment
}