package com.xoxoton.svaped.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginDO(
    @Expose @SerializedName("token") val token: String,
    @Expose @SerializedName("nickName") val nickName: String
)