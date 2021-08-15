package com.mobiquity.views

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MobiquityApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}