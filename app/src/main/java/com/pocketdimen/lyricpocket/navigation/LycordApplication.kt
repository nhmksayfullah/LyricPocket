package com.pocketdimen.lyricpocket.navigation

import android.app.Application
import com.pocketdimen.lyricpocket.di.AppModule
import com.pocketdimen.lyricpocket.di.AppModuleImpl

class LycordApplication: Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}