package com.xoxoton.svaped.ui.features.main

import com.xoxoton.svaped.data.model.BikeDO
import com.xoxoton.svaped.data.remote.SvapedApi
import com.xoxoton.svaped.network.constants.NetworkConstants.BIKE_NEARBY_REQUEST_CODE
import com.xoxoton.svaped.network.constants.NetworkConstants.BIKE_NEARBY_VERSION
import com.xoxoton.svaped.network.constants.NetworkConstants.DEFAULT_INDUSTRY_TYPE_VALUE
import com.xoxoton.svaped.network.constants.NetworkConstants.ROSTOV_LAT
import com.xoxoton.svaped.network.constants.NetworkConstants.ROSTOV_LNG
import com.xoxoton.svaped.data.local.AuthPrefs
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainRepository(private val service: SvapedApi, private val authPrefs: AuthPrefs) {

    fun getBikesNearby(): Observable<List<BikeDO>> =
        service.getBikesNearby(
            BIKE_NEARBY_REQUEST_CODE,
            DEFAULT_INDUSTRY_TYPE_VALUE,
            BIKE_NEARBY_VERSION,
            authPrefs.authToken, ROSTOV_LAT, ROSTOV_LNG)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }


}