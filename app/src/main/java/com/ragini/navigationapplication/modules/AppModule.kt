package com.ragini.navigationapplication.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val navApplication: NavApplication) {
    @Provides
    @Singleton
    fun provideContext(): Context = navApplication
}