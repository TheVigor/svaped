package com.xoxoton.svaped.ui

import android.app.Application
import com.xoxoton.svaped.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SvapedApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SvapedApp)

            modules(listOf(
                localModule(),
                remoteModule(),
                loginModule(),
                mainModule(),
                parkingModule()))
        }

    }

}