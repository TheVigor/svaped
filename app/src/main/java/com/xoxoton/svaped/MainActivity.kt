package com.xoxoton.svaped

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn = findViewById<Button>(R.id.map_button)

        btn.setOnClickListener(View.OnClickListener() {
            val intent = Intent(this@MainActivity, MapActivity::class.java)
            startActivity(intent)
        })
    }
}
