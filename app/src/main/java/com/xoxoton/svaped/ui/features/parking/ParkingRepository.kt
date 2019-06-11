package com.xoxoton.svaped.ui.features.parking

import com.xoxoton.svaped.data.model.ParkingPointDO
import com.xoxoton.svaped.data.remote.SvapedApi
import com.xoxoton.svaped.network.constants.NetworkConstants
import com.xoxoton.svaped.network.constants.NetworkConstants.BIKE_NEARBY_VERSION
import com.xoxoton.svaped.network.constants.NetworkConstants.DEFAULT_INDUSTRY_TYPE_VALUE
import com.xoxoton.svaped.network.constants.NetworkConstants.GET_PARKING_POINTS_REQUEST_CODE
import com.xoxoton.svaped.network.constants.NetworkConstants.ROSTOV_LAT
import com.xoxoton.svaped.network.constants.NetworkConstants.ROSTOV_LNG
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ParkingRepository(private val service: SvapedApi) {

    fun getParkingPoints(): Observable<List<ParkingPointDO>> =
        service.getParkingPoints(
            GET_PARKING_POINTS_REQUEST_CODE,
            NetworkConstants.token, ROSTOV_LAT, ROSTOV_LNG)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }


    companion object {
        @Volatile
        private var INSTANCE: ParkingRepository? = null

        fun getInstance(service: SvapedApi): ParkingRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ParkingRepository(service).also {
                    INSTANCE = it
                }
            }
        }
    }
}