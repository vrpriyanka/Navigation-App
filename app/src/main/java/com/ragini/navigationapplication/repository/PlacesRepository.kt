package com.ragini.navigationapplication.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ragini.navigationapplication.model.PlacesModel
import com.ragini.navigationapplication.room.PlacesData
import com.ragini.navigationapplication.room.RoomPlacesDataSource
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PlacesRepository(private val roomPlacesDataSource: RoomPlacesDataSource) : Repository {

    override fun getPlacesList(): LiveData<List<PlacesModel>> {
        val roomPlacesDao = roomPlacesDataSource.place()
        val mutableLiveData = MutableLiveData<List<PlacesModel>>()

        val disposable = roomPlacesDao.getAllPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ placesList ->
                mutableLiveData.value = transform(placesList)
            }, { t: Throwable? -> t!!.printStackTrace() })
        allCompositeDisposable.add(disposable)
        return mutableLiveData

    }

    companion object {
        @Volatile
        private var INSTANCE: PlacesRepository? = null

        fun getInstance(context: Context): PlacesRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: loadRepository(context).also { INSTANCE = it }
            }

        private fun loadRepository(context: Context): PlacesRepository {
            return PlacesRepository(RoomPlacesDataSource.buildPersistentPlaces(context))
        }
    }

    val allCompositeDisposable: MutableList<Disposable> = arrayListOf()

    override fun getTotalPlaces(): Flowable<Int> {
        return roomPlacesDataSource.place().getPlacesTotal()
    }

    override fun addPlaces() {
        val placesEntityList = RoomPlacesDataSource.getAllPlaces()
        roomPlacesDataSource.place().insertAll(placesEntityList)
    }

    private fun transform(places: List<PlacesData>): List<PlacesModel> {
        val placesList = ArrayList<PlacesModel>()
        places.forEach {
            placesList.add(
                PlacesModel(
                    it.place,
                    it.address,
                    it.latitude,
                    it.longitude
                )
            )
        }
        return placesList
    }
}