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

    override fun onResume() {
        super.onResume()
        if (bottom_navigation_view != null) {
            bottom_navigation_view.menu.getItem(navNumber).isChecked = true
        }
    }

    private fun goToSilent(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }

    private fun goToLogin() {
        authPrefs.dropToken()
        goToSilent(LoginActivity::class.java)
    }

    private fun goToHome() {
        goToSilent(MainActivity::class.java)
    }

    fun setupBottomNavigation() {
        bottom_navigation_view.setIconSize(29f, 29f)

        bottom_navigation_view.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.nav_item_home && navNumber == 0) {
                return@setOnNavigationItemSelectedListener true
            }

            if (it.itemId == R.id.nav_item_log_out && navNumber == 1) {
                return@setOnNavigationItemSelectedListener true
            }

            when (it.itemId) {
                R.id.nav_item_home -> {
                    goToHome()
                    true
                }
                R.id.nav_item_log_out -> {
                    goToLogin()
                    true
                }
                else -> false
            }
        }
    }

}