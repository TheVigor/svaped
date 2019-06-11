package com.xoxoton.svaped.data.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.xoxoton.svaped.network.constants.NetworkConstants.API_BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SvapedClient {
    companion object {
        @Volatile
        private var INSTANCE: SvapedApi? = null

        fun getInstance(): SvapedApi {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?:
                Retrofit.Builder().baseUrl(API_BASE_URL)
                    .client(HttpClient.getInstance())
                    .addConverterFactory(GsonConverterFactory.create(gson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create<SvapedApi>(SvapedApi::class.java).also {
                        INSTANCE = it
                    }
            }
        }

        fun gson() : Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }
}