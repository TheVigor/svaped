package com.xoxoton.svaped.ui.error

import androidx.annotation.IntDef
import com.xoxoton.svaped.ui.error.EmptyViewMode.Companion.MODE_INCORRECT_LOGIN
import com.xoxoton.svaped.ui.error.EmptyViewMode.Companion.MODE_NO_BIKES
import com.xoxoton.svaped.ui.error.EmptyViewMode.Companion.MODE_NO_PARKINGS

@IntDef(
    MODE_NO_BIKES,
    MODE_NO_PARKINGS,
    MODE_INCORRECT_LOGIN
)
@EmptyViewMode
annotation class EmptyViewMode {
    companion object {
        const val MODE_NO_BIKES = 0
        const val MODE_NO_PARKINGS = 1
        const val MODE_INCORRECT_LOGIN = 2

    }
}