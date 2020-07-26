package com.craftablescience.animetracker.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.craftablescience.animetracker.R

class LaunchActivity : AppCompatActivity() {
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_path), Context.MODE_PRIVATE)

        if (!sharedPreferences.getBoolean("firsttimesetup_complete", false)) {
            startActivity(Intent(this, FirstTimeSetupActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}