package com.xoxoton.svaped.ui.features.about

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.vansuita.materialabout.builder.AboutBuilder
import com.xoxoton.svaped.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        initToolbar()
        initAboutView()
    }

    private fun initAboutView() {
        val builder = AboutBuilder.with(this)
            .setAppIcon(R.mipmap.ic_launcher)
            .setAppName(R.string.app_name)
            .setPhoto(R.drawable.bike_marker)
            .setCover(R.mipmap.profile_cover)
            .setLinksAnimated(true)
            .setDividerDashGap(13)
            .setName("SVAped")
            .setLinksColumnsCount(1)
            .setBrief("They see me rollin...")
            .addGitHubLink("TheVigor/svaped")
            .setVersionNameAsAppSubTitle()
            .setWrapScrollView(true)
            .setShowAsCard(true)

        val view = builder.build()
        container.addView(view)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        supportActionBar?.setTitle(R.string.about)
        appBarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent30))
    }
}
