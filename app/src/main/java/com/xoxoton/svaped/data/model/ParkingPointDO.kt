package com.xoxoton.svaped.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ParkingPointDO(
    @SerializedName("city_id") val cityId: Long,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lng") val longitude: Double,
    @SerializedName("detail") val polyString: String,
    @SerializedName("name") val name: String,
    @SerializedName("typeStr") val typeStr: String,
    @SerializedName("image_small") val image: String,

    @SerializedName("note") val note: String
): Parcelable {
    fun getCityById() = when(cityId) {
        12L -> "Ростов"
        else -> "Unknown"
    }
}