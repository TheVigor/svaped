package com.xoxoton.svaped.di

import com.xoxoton.svaped.data.local.AuthPrefs
import org.koin.dsl.module

fun localModule() = module {

    single {
        AuthPrefs(get())
    }

}