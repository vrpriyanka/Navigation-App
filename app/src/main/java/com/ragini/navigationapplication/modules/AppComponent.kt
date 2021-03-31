package com.ragini.navigationapplication.modules

import com.ragini.navigationapplication.viewmodel.PlacesViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, RoomModule::class])
@Singleton
interface AppComponent {
    fun inject(placesViewModel: PlacesViewModel)
}