package com.xoxoton.svaped.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.xoxoton.svaped.R

fun ImageView.loadImage(image: String) =
    Glide
        .with(this)
        .load(image)
        .centerCrop()
        .placeholder(R.drawable.parking_marker)
        .into(this)
