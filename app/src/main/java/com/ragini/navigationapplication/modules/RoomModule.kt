package com.ragini.navigationapplication.modules

import android.content.Context
import com.ragini.navigationapplication.room.RoomPlacesDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideRoomPlacesDataSource(context: Context) =
        RoomPlacesDataSource.buildPersistentPlaces(context)
}