package com.xoxoton.svaped.ui.features.login

import com.xoxoton.svaped.data.remote.SvapedApi
import com.xoxoton.svaped.network.constants.NetworkConstants.LOGIN_REQUEST_CODE
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.security.MessageDigest

class LoginRepository(private val service: SvapedApi) {
    fun prepareLogInParams(phone: String, password: String): HashMap<String, String> {
        val md = MessageDigest.getInstance("MD5")
        val hashedPass = md.digest(password.toByteArray())
        val hashedPassStr = hashedPass.joinToString("") { "%02x".format(it) }
        return hashMapOf(
            "industryType" to "7",
            "requestType" to LOGIN_REQUEST_CODE.toString(),
            "phone" to phone,
            "phoneCode" to "7",
            "password" to hashedPassStr
        )
    }

    fun login(phone: String, pass: String): Observable<String> =
        service.login(
            prepareLogInParams(phone, pass)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.data.token
            }


    companion object {
        @Volatile
        private var INSTANCE: LoginRepository? = null

        fun getInstance(service: SvapedApi): LoginRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LoginRepository(service).also {
                    INSTANCE = it
                }
            }
        }
    }
}