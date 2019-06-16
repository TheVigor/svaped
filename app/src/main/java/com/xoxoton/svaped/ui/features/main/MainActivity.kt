package com.xoxoton.svaped.ui.features.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xoxoton.svaped.R
import android.widget.Button
import com.xoxoton.svaped.data.local.AuthPrefs
import com.xoxoton.svaped.ui.features.login.LogInActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val authPrefs: AuthPrefs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!authPrefs.isUserLoggedIn()) {
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }

        activateHandlers()

    }

    private fun activateHandlers() {
        val btn = findViewById<Button>(R.id.map_button)

        btn.setOnClickListener{
            val intent = Intent(this@MainActivity, MapActivity::class.java)
            startActivity(intent)
        }

    }
}
