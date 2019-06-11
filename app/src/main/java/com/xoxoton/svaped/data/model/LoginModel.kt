package com.xoxoton.svaped.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginModel (
    @Expose @SerializedName("data") val data: LoginDO
)