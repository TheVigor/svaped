package com.xoxoton.svaped.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

enum class BikeCategory {
    GREEN,
    YELLOW,
    RED
}

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
) {
    fun getBikeCategory(): BikeCategory {
        val category = number.takeLast(4).toInt()
        return when (category) {
            in 0..900 -> BikeCategory.GREEN
            in 900..1000 -> BikeCategory.YELLOW
            in 1000..9999 -> BikeCategory.RED
            else -> BikeCategory.GREEN
        }
    }
}

