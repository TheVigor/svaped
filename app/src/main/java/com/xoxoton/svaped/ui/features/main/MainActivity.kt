package com.xoxoton.svaped.ui.features.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xoxoton.svaped.R
import android.widget.Button
import com.xoxoton.svaped.network.constants.NetworkConstants
import com.xoxoton.svaped.ui.features.login.LogInActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (NetworkConstants.token.isEmpty()) {
            val intent = Intent(applicationContext, LogInActivity::class.java)
            startActivityForResult(intent, 0)
            finish()
        } else {
            activateHandlers()
        }
    }

    private fun activateHandlers() {
        val btn = findViewById<Button>(R.id.map_button)

        btn.setOnClickListener{
            val intent = Intent(this@MainActivity, MapActivity::class.java)
            startActivity(intent)
        }

    }
}
