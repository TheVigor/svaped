package com.xoxoton.svaped.ui.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.xoxoton.svaped.R
import com.xoxoton.svaped.data.local.AuthPrefs
import com.xoxoton.svaped.ui.features.login.LoginActivity
import com.xoxoton.svaped.ui.features.main.MainActivity
import kotlinx.android.synthetic.main.bottom_navigation_view.*
import org.koin.android.ext.android.inject

abstract class BaseActivity(val navNumber: Int) : AppCompatActivity() {

    val authPrefs: AuthPrefs by inject()

    private fun goToSilent(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }

    fun goToLogin() {
        authPrefs.dropToken()
        goToSilent(LoginActivity::class.java)
    }

    fun goToHome() {
        goToSilent(MainActivity::class.java)
    }

    fun goToSettings() {

    }
}