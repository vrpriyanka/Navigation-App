package com.ragini.navigationapplication.modules

import android.app.Application
import javax.inject.Inject

class NavApplication : Application() {
    companion object {
        @set:Inject
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        daggerInitialization()
    }

    private fun daggerInitialization() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule())
            .build()
    }
}