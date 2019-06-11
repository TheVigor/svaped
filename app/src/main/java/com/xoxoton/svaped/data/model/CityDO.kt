package com.xoxoton.svaped.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class CityDO(
    val id: Long?,
    val code: String,
    val name: String,
    val note: String,
    @SerializedName("area_detail") val areaDetail: String,
    @SerializedName("area_lat") val latitude: Double?,
    @SerializedName("area_lng") val longitude: Double?,
    val currency: String,
    val test: Boolean?
): Serializable