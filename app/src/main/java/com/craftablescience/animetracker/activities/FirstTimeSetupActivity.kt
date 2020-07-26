package com.craftablescience.animetracker.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.craftablescience.animetracker.R

class FirstTimeSetupActivity : AppCompatActivity() {
    private lateinit var anilistButton : Button
    private lateinit var continueButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firsttimesetup)

        anilistButton = findViewById(R.id.firsttimesetup_activity_anilist)
        anilistButton.setOnClickListener {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(String.format("https://anilist.co/api/v2/oauth/authorize?client_id=%s&response_type=token",
                                                   getString(R.string.anilist_client_id)))
            startActivity(openUrl)
            finish()
        }
        continueButton = findViewById(R.id.firsttimesetup_activity_button)
        continueButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}