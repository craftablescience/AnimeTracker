package com.craftablescience.animetracker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.viewpager.widget.ViewPager
import com.craftablescience.animetracker.util.PagerAdapter
import com.craftablescience.animetracker.R
import com.craftablescience.animetracker.screens.MainScreen
import com.craftablescience.animetracker.screens.getMainScreenForMenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var mainToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // elements
        viewPager = findViewById(R.id.main_view_pager)
        bottomNavigationView = findViewById(R.id.main_bottom_navigation_view)
        pagerAdapter = PagerAdapter(supportFragmentManager)
        mainToolbar = findViewById(R.id.main_toolbar)

        // set up top bar
        mainToolbar.inflateMenu(R.menu.main_toolbar_menu)

        // set items to be displayed
        pagerAdapter.setItems(
            arrayListOf(
                MainScreen.CURRENTLYWATCHING,
                MainScreen.COMPLETED,
                MainScreen.ONHOLD,
                MainScreen.DROPPED,
                MainScreen.PLANNINGTOWATCH
            )
        )

        // show default screen
        val defaultScreen = MainScreen.CURRENTLYWATCHING
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

        mainToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main_toolbar_settings -> {
                    startActivity(Intent(this, SettingsActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Scrolls `ViewPager` to show the provided screen
     */
    private fun scrollToScreen(mainScreen: MainScreen) {
        val screenPosition = pagerAdapter.getItems().indexOf(mainScreen)
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
        getMainScreenForMenuItem(
            menuItem.itemId
        )?.let {
            scrollToScreen(it)
            return true
        }
        return false
    }
}