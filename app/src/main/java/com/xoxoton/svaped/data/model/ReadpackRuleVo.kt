package com.xoxoton.svaped.data.model

import com.google.gson.annotations.SerializedName


class ReadpackRuleVo(
    @SerializedName("coupon_num") val couponNumber: Int,
    @SerializedName("must_in_area") val mustInArea: Int,
    val type: Int
)