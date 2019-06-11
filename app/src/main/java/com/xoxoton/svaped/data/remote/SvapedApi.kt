package com.xoxoton.svaped.data.remote

import com.xoxoton.svaped.data.model.*
import io.reactivex.Completable
import io.reactivex.Observable
import org.jetbrains.annotations.NotNull
import retrofit2.http.*


interface SvapedApi {

    @GET("app/bike")
    @NotNull
    fun blockBike(
        @Query("requestType") paramInt: Int,
        @Query("token") @NotNull paramString1: String,
        @Query("bikeNumber") @NotNull paramString2: String
    ): Observable<String>

    @GET("common/heartbeat")
    @NotNull
    fun checkServer(@Query("requestType") paramInt: Int): Completable

    @GET("app/bike")
    @NotNull
    fun getBikeUseInfo(
        @Query("requestType") paramInt1: Int,
        @Query("token") @NotNull paramString: String,
        @Query("versionCode") paramInt2: Int
    ): Observable<BikeModel>

    @GET("app/bike")
    @NotNull
    fun getBikesNearby(
        @Query("requestType") paramInt1: Int,
        @Query("industryType") paramInt2: Int,
        @Query("version") @NotNull paramString1: String,
        @Query("token") @NotNull paramString2: String,
        @Query("lat") paramDouble1: Double,
        @Query("lng") paramDouble2: Double
    ): Observable<BikeModel>

    @GET("app/city")
    @NotNull
    fun getCityList(
        @Query("requestType") paramInt: Int,
        @Query("token") @NotNull paramString: String
    ): Observable<List<CityDO>>

    @GET("app/city")
    @NotNull
    fun getClosestCity(
        @Query("requestType") paramInt: Int,
        @Query("token") @NotNull paramString: String,
        @Query("lat") paramDouble1: Double,
        @Query("lng") paramDouble2: Double
    ): Observable<CityDO>

    @GET("app/bike")
    @NotNull
    fun getParkingPoints(
        @Query("requestType") paramInt: Int,
        @Query("token") @NotNull paramString: String,
        @Query("lat") paramDouble1: Double,
        @Query("lng") paramDouble2: Double
    ): Observable<ParkingPointModel>

    @GET("json")
    @NotNull
    fun getRoute(
        @Query("token") @NotNull paramString1: String,
        @Query("origin") @NotNull paramString2: String,
        @Query("destination") @NotNull paramString3: String,
        @Query("mode") @NotNull paramString4: String,
        @Query("key") @NotNull paramString5: String
    ): Observable<String>

    @POST("app/city")
    @NotNull
    fun setUserCity(
        @Query("requestType") paramInt: Int,
        @Query("token") @NotNull paramString: String,
        @Query("cityId") paramLong: Long
    ): Completable

    @POST("app/bike")
    @NotNull
    fun subscribeBike(
        @Query("requestType") paramInt: Int,
        @Query("token") @NotNull paramString1: String,
        @Query("bikeId") @NotNull paramString2: String
    ): Observable<String>

    @POST("app/bike")
    @NotNull
    fun unlockBike(
        @Query("requestType") paramInt: Int,
        @Query("token") @NotNull paramString1: String,
        @Query("bikeNumber") @NotNull paramString2: String,
        @Query("startLat") paramDouble1: Double,
        @Query("startLng") paramDouble2: Double
    ): Observable<String>

    @POST("app/bike")
    @NotNull
    fun unsubscribeBike(
        @Query("requestType") paramInt: Int,
        @Query("token") @NotNull paramString: String
    ): Observable<String>

    @POST("app/bike")
    @NotNull
    fun updateBikeWay(
        @Query("requestType") paramInt: Int,
        @Query("token") @NotNull paramString1: String,
        @Query("bikeNumber") @NotNull paramString2: String,
        @Query("lat") paramDouble1: Double,
        @Query("lng") paramDouble2: Double
    ): Observable<String>

    @POST("app/login")
    @NotNull
    fun login(@QueryMap loginParams: Map<String, String>): Observable<LoginModel>
}