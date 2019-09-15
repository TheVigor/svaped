package com.xoxoton.svaped.ui.features.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.xoxoton.svaped.R
import com.xoxoton.svaped.ui.base.BaseActivity

class SettingsFragment: PreferenceFragmentCompat() {

    companion object {
        internal fun newInstance() = SettingsFragment()

        const val KEY_ABOUT_TAG = "key_about"
        const val KEY_LOG_OUT = "key_log_out"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_settings, rootKey)
    }

    override fun onPreferenceTreeClick(preference: androidx.preference.Preference?): Boolean {
        return when {
            preference?.key == KEY_ABOUT_TAG -> true
            preference?.key == KEY_LOG_OUT -> {
                (requireActivity() as BaseActivity).apply {
                    authPrefs.dropToken()
                    finish()
                }
                true

            }
            else -> super.onPreferenceTreeClick(preference)
        }

    }
}