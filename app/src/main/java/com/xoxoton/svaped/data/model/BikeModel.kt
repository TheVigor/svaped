package com.xoxoton.svaped.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BikeModel(
    @Expose @SerializedName("code") val code: Int,
    @Expose @SerializedName("data") val data: List<BikeDO>
)
