package com.xoxoton.svaped.ui.features.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.xoxoton.svaped.R
import com.xoxoton.svaped.ui.extensions.showToast
import com.xoxoton.svaped.ui.features.main.MainActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    val loginViewModel: LogInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        loginViewModel.contentState.observe(
            this,
            Observer { content ->
                loginViewModel.repository.authPrefs.authToken = content
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            })
        loginViewModel.errorState.observe(this,
            Observer {
                showToast("Can't login!\nSomething goes wrong")
                Log.e(this.javaClass.canonicalName, "ERROR LOGGING IN")
            }
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.logInButton).setOnClickListener {
            val phone = findViewById<EditText>(R.id.logInPhoneEdit).text.toString()
            val pass = findViewById<EditText>(R.id.logInPassEdit).text.toString()
            loginViewModel.login(phone, pass)
        }

    }
}
