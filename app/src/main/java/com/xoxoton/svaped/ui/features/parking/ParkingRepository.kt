package com.xoxoton.svaped.ui.features.parking

import com.xoxoton.svaped.data.model.ParkingPointDO
import com.xoxoton.svaped.data.remote.SvapedApi
import com.xoxoton.svaped.network.constants.NetworkConstants.GET_PARKING_POINTS_REQUEST_CODE
import com.xoxoton.svaped.network.constants.NetworkConstants.ROSTOV_LAT
import com.xoxoton.svaped.network.constants.NetworkConstants.ROSTOV_LNG
import com.xoxoton.svaped.data.local.AuthPrefs
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ParkingRepository(private val service: SvapedApi, private val authPrefs: AuthPrefs) {

    fun getParkingPoints(): Observable<List<ParkingPointDO>> =
        service.getParkingPoints(
            GET_PARKING_POINTS_REQUEST_CODE,
            authPrefs.authToken, ROSTOV_LAT, ROSTOV_LNG)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }

}