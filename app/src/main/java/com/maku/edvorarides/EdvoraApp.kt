package com.maku.edvorarides

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class EdvoraApp: Application() {

    // initiate analytics, crashlytics, etc

    override fun onCreate() {
        super.onCreate()
        Timber.plant()
    }

}