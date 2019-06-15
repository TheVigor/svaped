package com.xoxoton.svaped.ui

import android.app.Application
import com.xoxoton.svaped.di.loginModule
import com.xoxoton.svaped.di.mainModule
import com.xoxoton.svaped.di.parkingModule
import com.xoxoton.svaped.di.remoteModule
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
                remoteModule(),
                loginModule(),
                mainModule(),
                parkingModule()))
        }

    }

}