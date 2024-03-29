package com.xoxoton.svaped.di

import com.xoxoton.svaped.data.local.AuthPrefs
import com.xoxoton.svaped.ui.features.login.LogInViewModel
import com.xoxoton.svaped.ui.features.login.LoginRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun loginModule() = module {

    single {
        LoginRepository(get(), get())
    }

    viewModel {
        LogInViewModel(get())
    }
}