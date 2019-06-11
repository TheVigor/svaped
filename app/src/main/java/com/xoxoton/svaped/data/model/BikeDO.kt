package com.xoxoton.svaped.data.model

import com.google.gson.annotations.SerializedName


class BikeDO(
    val bid: Int,
    @SerializedName("city_id") val cityId: Long,
    @SerializedName("add_date") val addDate: String,
    val errorStr: String,
    @SerializedName("error_status") val errorStatus: Long,
    @SerializedName("gLat")val latitude: Double,
    @SerializedName("gLng") val longitude: Double,
    @SerializedName("gTime") val time: String,
    val gpsNumber: Long,
    val gsm: Long,
    val heartTime: String,
    val imei: String,
    val lastRideDate: String,
    val mac: String,
    val number: String,
    val power: Int,
    val powerPercent: Int,
    val price: Double,
    val readpack: Int,
    val readpackRuleVo: ReadpackRuleVo,
    val reportCount: Int,
    val rideCount: Int,
    val serverIp: String,
    val status: Int,
    val statusStr: String,
    val statusTime: String,
    val typeCount: Int,
    val typeId: Long,
    val useStatus: Int,
    val version: String,
    val versionTime: String
)

