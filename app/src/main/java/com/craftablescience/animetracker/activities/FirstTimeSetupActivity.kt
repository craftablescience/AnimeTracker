package com.craftablescience.animetracker.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.craftablescience.animetracker.R

class FirstTimeSetupActivity : AppCompatActivity() {
    private lateinit var continueButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firsttimesetup)

        continueButton = findViewById(R.id.firsttimesetup_activity_button)
        continueButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}