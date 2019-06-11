package com.xoxoton.svaped.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BikeDO(
    @Expose @SerializedName("add_date") val addDate: String,
    @Expose @SerializedName("bid") val bid: Int,

    @Expose @SerializedName("city_id") val cityId: Long,

    @Expose @SerializedName("error_status") val errorStatus: Long,
    @Expose @SerializedName("errorStr") val errorStr: String,

    @Expose @SerializedName("gLat")val latitude: Double,
    @Expose @SerializedName("gLng") val longitude: Double,

    @Expose @SerializedName("imei") val imei: String,

    @Expose @SerializedName("number") val number: String
)

