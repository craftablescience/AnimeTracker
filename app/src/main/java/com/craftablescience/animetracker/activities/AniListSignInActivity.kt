package com.craftablescience.animetracker.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.craftablescience.animetracker.R

class AniListSignInActivity : AppCompatActivity() {
    private lateinit var sharedPreferences : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        sharedPreferences = getSharedPreferences(getString(R.string.shared_prefs_path), Context.MODE_PRIVATE)

        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        val appLinkAction = intent.action
        val appLinkData: Uri? = intent.data
        if (Intent.ACTION_VIEW == appLinkAction) {
            val data = appLinkData.toString()
            val apiKey = data.substring(data.indexOf("=") + 1, data.indexOf("&"))
            if (apiKey != "access_denied") {
                saveProfile(apiKey)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, getString(R.string.toast_anilist_authorize_fail), Toast.LENGTH_LONG).show()
                startActivity(Intent(this, FirstTimeSetupActivity::class.java))
                finish()
            }
        }
    }

    private fun saveProfile(key : String) {
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putString("anilist_api_key", key)
        sharedPreferencesEditor.putBoolean("firsttimesetup_complete", true)
        sharedPreferencesEditor.commit()
    }
}