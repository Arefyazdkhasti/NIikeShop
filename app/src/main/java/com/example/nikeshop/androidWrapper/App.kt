package com.example.nikeshop.androidWrapper

import android.app.Application
import com.example.nikeshop.di.apiModules
import com.example.nikeshop.di.fragmentModules
import com.example.nikeshop.di.modelModules
import com.example.nikeshop.di.presenterModules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            applicationContext,
            listOf(
                fragmentModules,
                modelModules,
                apiModules,
                presenterModules
                )
        )
    }
}