package com.ragini.navigationapplication.repository

import androidx.lifecycle.LiveData
import com.ragini.navigationapplication.model.PlacesModel
import io.reactivex.Flowable

interface Repository {
    fun getTotalPlaces(): Flowable<Int>
    fun addPlaces()
    fun getPlacesList(): LiveData<List<PlacesModel>>
}