package com.xoxoton.svaped.di

import com.xoxoton.svaped.ui.features.parking.ParkingRepository
import com.xoxoton.svaped.ui.features.parking.ParkingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun parkingModule() = module {

    single {
        ParkingRepository(get(), get())
    }

    viewModel {
        ParkingViewModel(get())
    }
}