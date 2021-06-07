package com.fghilmany.newsdetection

import android.app.Application
import com.fghilmany.newsdetection.core.di.networkModule
import com.fghilmany.newsdetection.core.di.repositoryModule
import com.fghilmany.newsdetection.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }

    }
}