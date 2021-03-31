package com.ragini.navigationapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PlacesData::class],
    version = 1
)

abstract class RoomPlacesDataSource : RoomDatabase() {
    abstract fun place(): RoomPlace

    companion object {
        fun buildPersistentPlaces(context: Context): RoomPlacesDataSource = Room.databaseBuilder(
            context.applicationContext,
            RoomPlacesDataSource::class.java,
            RoomContract.DATABASE_PLACES
        ).build()

        fun getAllPlaces(): List<PlacesData> {
            val mutablePlacesList = mutableListOf<PlacesData>()
            mutablePlacesList.add(
                createEntity(
                    "Bonaventure Always Clean Family Friendly Skating Center",
                    "24505 Halsted Rd, Farmington Hills, MI 48335, USA",
                    42.472980, -83.417070
                )
            )

            mutablePlacesList.add(
                createEntity(
                    "Michigan Opera Theatre",
                    "1526 Broadway St, Detroit, MI 48226, USA",
                    42.336342, -83.049347
                )
            )

            mutablePlacesList.add(
                createEntity(
                    "Michigan Central",
                    "2001 15th St, Detroit, MI 48216, USA",
                    42.328690, -83.077507
                )
            )

            mutablePlacesList.add(
                createEntity(
                    "United Way for Southeastern Michigan",
                    "3011 W Grand Blvd #500, Detroit, MI 48202, USA",
                    42.369160, -83.077011
                )
            )

            mutablePlacesList.add(
                createEntity(
                    "Henry Ford Museum of American Innovation",
                    "20900 Oakwood Blvd, Dearborn, MI 48124, USA",
                    42.302238, -83.234131
                )
            )
            return mutablePlacesList
        }

        private fun createEntity(
            name: String,
            address: String,
            latitude: Double,
            longitude: Double
        ) =
            PlacesData(0, name, address, latitude, longitude)
    }
}