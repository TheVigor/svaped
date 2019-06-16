package com.xoxoton.svaped.di

import com.xoxoton.svaped.ui.features.main.MainRepository
import com.xoxoton.svaped.ui.features.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun mainModule() = module {

    single {
        MainRepository(get(), get())
    }

    viewModel {
        MainViewModel(get())
    }

}