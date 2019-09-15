package com.xoxoton.svaped.ui.features.settings

import android.os.Bundle
import androidx.fragment.app.transaction
import com.xoxoton.svaped.R
import com.xoxoton.svaped.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        initToolbar()

        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                replace(container.id, SettingsFragment.newInstance())
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.setTitle(R.string.settings)
    }
}
