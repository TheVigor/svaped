package com.xoxoton.svaped.ui.features.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xoxoton.svaped.R
import android.view.View
import com.xoxoton.svaped.data.remote.SvapedClient
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xoxoton.svaped.ui.extensions.showToast

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.map_button)

        btn.setOnClickListener{
            val intent = Intent(this@MainActivity, MapActivity::class.java)
            startActivity(intent)
        }

        mainViewModel = ViewModelProviders.of(this, MoviesViewModelFactory(
            MainRepository.getInstance(SvapedClient.getInstance())))
            .get(MainViewModel::class.java)

        mainViewModel.loadingState.observe(this,
            Observer { state ->
                state?.let {
                    //progressBar.visibility = if (state) View.VISIBLE else View.GONE
                    //showToast("Loading...")
                }
            })

        mainViewModel.errorState.observe(this,
            Observer { code ->
                code?.let {
                    //emptyView.visibility = View.VISIBLE
                    //emptyView.setMode(code)
                    //showToast("Error")
                }
            })

        mainViewModel.contentState.observe(this,
            Observer { content ->
                if (content != null) {
                    showToast("Bikes count: " + content.size.toString())
                }
            })

        mainViewModel.getBikesNearby()

    }
}
