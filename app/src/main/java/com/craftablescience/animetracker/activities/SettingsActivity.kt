package com.craftablescience.animetracker.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.craftablescience.animetracker.util.PagerAdapter
import com.craftablescience.animetracker.R
import com.craftablescience.animetracker.screens.ActivityScreen
import com.craftablescience.animetracker.screens.SettingsScreen
import com.craftablescience.animetracker.screens.getSettingsScreenForMenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class SettingsActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var settingsToolbar: androidx.appcompat.widget.Toolbar

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // elements
        viewPager = findViewById(R.id.settings_view_pager)
        bottomNavigationView = findViewById(R.id.settings_bottom_navigation_view)
        pagerAdapter = PagerAdapter(supportFragmentManager)
        settingsToolbar = findViewById(R.id.settings_toolbar)

        // prefs
        sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_path), Context.MODE_PRIVATE)

        // set items to be displayed
        pagerAdapter.setItems(
            arrayListOf(
                SettingsScreen.ACCOUNT,
                SettingsScreen.UI,
                SettingsScreen.ABOUT
            )
        )

        // show default screen
        val defaultScreen = SettingsScreen.ACCOUNT
        scrollToScreen(defaultScreen)
        selectBottomNavigationViewMenuItem(defaultScreen.menuItemId)

        // set listener for item selection in bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        // attach adapter to view pager and make it select bottom navigation
        // menu item and change title to proper values when selected
        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                val selectedScreen = pagerAdapter.getItems()[position]
                selectBottomNavigationViewMenuItem(selectedScreen.menuItemId)
            }
        })

        settingsToolbar.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    /**
     * Saves a string to SharedPreferences
     */
    private fun saveStringSetting(key : String, value : String) {
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putString(key, value)
        sharedPreferencesEditor.apply()
    }
    /**
     * Saves a boolean to SharedPreferences
     */
    private fun saveBooleanSetting(key : String, value : Boolean) {
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putBoolean(key, value)
        sharedPreferencesEditor.apply()
    }
    /**
     * Saves an int to SharedPreferences
     */
    private fun saveIntSetting(key : String, value : Int) {
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putInt(key, value)
        sharedPreferencesEditor.apply()
    }

    /**
     * Scrolls `ViewPager` to show the provided screen
     */
    private fun scrollToScreen(activityScreen: ActivityScreen) {
        val screenPosition = pagerAdapter.getItems().indexOf(activityScreen)
        if (screenPosition != viewPager.currentItem) {
            viewPager.currentItem = screenPosition
        }
    }

    /**
     * Selects the specified item in the bottom navigation view
     */
    private fun selectBottomNavigationViewMenuItem(@IdRes menuItemId: Int) {
        bottomNavigationView.setOnNavigationItemSelectedListener(null)
        bottomNavigationView.selectedItemId = menuItemId
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    /**
     * Listener implementation for registering bottom navigation clicks
     */
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        getSettingsScreenForMenuItem(
            menuItem.itemId
        )?.let {
            scrollToScreen(it)
            return true
        }
        return false
    }
}