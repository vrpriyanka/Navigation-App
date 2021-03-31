package com.ragini.navigationapplication.Utils

import android.content.Context
import com.ragini.navigationapplication.repository.PlacesRepository
import com.ragini.navigationapplication.viewmodel.PlaceViewModelFactory

class Injectors {
    companion object {
        fun providePlaceRepository(context: Context): PlaceViewModelFactory {
            return PlaceViewModelFactory(PlacesRepository.getInstance(context))
        }
    }
}