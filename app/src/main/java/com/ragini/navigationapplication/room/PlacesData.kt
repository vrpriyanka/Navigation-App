package com.ragini.navigationapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = RoomContract.TABLE_PLACES)
data class PlacesData(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var place: String,
    var address: String,
    var latitude: Double,
    var longitude: Double
)