package com.xoxoton.svaped.ui.error

import androidx.annotation.IntDef
import com.xoxoton.svaped.ui.error.EmptyViewMode.Companion.MODE_NO_BIKES
import com.xoxoton.svaped.ui.error.EmptyViewMode.Companion.MODE_NO_PARKINGS

@IntDef(
    MODE_NO_BIKES,
    MODE_NO_PARKINGS
)
@EmptyViewMode
annotation class EmptyViewMode {
    companion object {
        const val MODE_NO_BIKES = 0
        const val MODE_NO_PARKINGS = 1
    }
}