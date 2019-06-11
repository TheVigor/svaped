package com.xoxoton.svaped.data.model


import com.google.gson.annotations.SerializedName

class ParkingPointDO(
    val id: Long,
    @SerializedName("city_id") val cityId: Long,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lng") val longitude: Double,
    @SerializedName("detail") val polyString: String,
    val name: String,
    val note: String,
    val image: String,
    val type: Int,
    val typeStr: String,
    @SerializedName("latLngs") val areaCoords: List<MapPointDO>?
)