package com.xoxoton.svaped.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xoxoton.svaped.R
import com.xoxoton.svaped.data.local.AuthPrefs
import com.xoxoton.svaped.data.model.ParkingPointDO
import com.xoxoton.svaped.ui.features.login.LoginActivity
import com.xoxoton.svaped.ui.features.main.MainActivity
import com.xoxoton.svaped.ui.features.parking.ParkingBottomSheetFragment
import com.xoxoton.svaped.ui.features.parking.ParkingBottomSheetFragment.Companion.PARKING_TAG
import com.xoxoton.svaped.ui.features.settings.SettingsActivity
import kotlinx.android.synthetic.main.bottom_navigation_view.*
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {

    val authPrefs: AuthPrefs by inject()

    fun checkAuth() {
        if (!authPrefs.isUserLoggedIn()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    fun showParkingInfoDialog(parkingPoint: ParkingPointDO) {
        val bundle = Bundle()
        bundle.putParcelable(PARKING_TAG, parkingPoint)

        val bottomSheetFragment = ParkingBottomSheetFragment()
        bottomSheetFragment.arguments = bundle
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

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
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}