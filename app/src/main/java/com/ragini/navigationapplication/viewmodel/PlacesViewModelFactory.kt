package com.ragini.navigationapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ragini.navigationapplication.repository.PlacesRepository

@Suppress("UNCHECKED_CAST")
class PlaceViewModelFactory (private val placesRepository: PlacesRepository): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlacesViewModel(placesRepository) as T
    }
}