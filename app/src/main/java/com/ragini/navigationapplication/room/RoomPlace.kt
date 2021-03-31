package com.ragini.navigationapplication.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface RoomPlace {
    @Query(RoomContract.SELECT_PLACES_COUNT)
    fun getPlacesTotal(): Flowable<Int>

    @Insert
    fun insertAll(places: List<PlacesData>)

    @Query(RoomContract.SELECT_PLACES)
    fun getAllPlaces(): Flowable<List<PlacesData>>
}