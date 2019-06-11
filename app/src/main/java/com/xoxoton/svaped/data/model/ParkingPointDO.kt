package com.xoxoton.svaped.data.model


import com.google.gson.annotations.SerializedName

class ParkingPointDO(
    @SerializedName("city_id") val cityId: Long,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lng") val longitude: Double,
    @SerializedName("detail") val polyString: String,
    @SerializedName("name") val name: String,
    @SerializedName("typeStr") val typeStr: String,
    @SerializedName("image_small") val image: String,

    @SerializedName("note") val note: String,

    @SerializedName("latLngs") val areaCoords: List<MapPointDO>?
)