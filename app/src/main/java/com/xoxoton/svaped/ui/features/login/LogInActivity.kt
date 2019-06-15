package com.xoxoton.svaped.ui.features.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.xoxoton.svaped.R
import com.xoxoton.svaped.data.remote.SvapedClient
import com.xoxoton.svaped.network.constants.NetworkConstants
import com.xoxoton.svaped.ui.extensions.showToast
import com.xoxoton.svaped.ui.features.main.ActivityResults
import com.xoxoton.svaped.ui.features.main.LogInViewModelFactory
import com.xoxoton.svaped.ui.features.main.MainActivity

class LogInActivity : AppCompatActivity() {
    private lateinit var logInViewModel: LogInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        logInViewModel = ViewModelProviders.of(
            this, LogInViewModelFactory(
                LoginRepository.getInstance(SvapedClient.getInstance())
            )
        ).get(LogInViewModel::class.java)
        logInViewModel.contentState.observe(
            this,
            Observer { content ->
                NetworkConstants.token = content
                setResult(ActivityResults.LOGIN_SUCCESS)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            })
        logInViewModel.errorState.observe(this,
            Observer {
                showToast("Can't login!\nSomething goes wrong")
                Log.e(this.javaClass.canonicalName, "ERROR LOGGING IN")
            }
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        findViewById<Button>(R.id.logInButton).setOnClickListener {
            val phone = findViewById<EditText>(R.id.logInPhoneEdit).text.toString()
            val pass = findViewById<EditText>(R.id.logInPassEdit).text.toString()
            logInViewModel.login(phone, pass)
        }

    }
}
