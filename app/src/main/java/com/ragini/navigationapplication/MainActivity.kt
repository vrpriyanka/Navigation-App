@file:Suppress("DEPRECATION")

package com.ragini.navigationapplication

import android.content.res.Configuration
import android.os.Bundle
import android.widget.AbsListView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ragini.navigationapplication.Utils.Constants
import com.ragini.navigationapplication.Utils.Injectors
import com.ragini.navigationapplication.data.PlaceAdapter
import com.ragini.navigationapplication.model.PlacesModel
import com.ragini.navigationapplication.viewmodel.PlaceViewModelFactory
import com.ragini.navigationapplication.viewmodel.PlacesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mGoogleMap: GoogleMap? = null
    private var placesViewModel: PlacesViewModel? = null
    private var placesList = ArrayList<PlacesModel>()
    private lateinit var adapter: PlaceAdapter
    private var position: Int = 0

    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap = googleMap
        loadMarkers()
        loadNext(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = PlaceAdapter(placesList)

        val layoutMan = LinearLayoutManager(this, orientationCheck(), false)
        listOfPlaces.layoutManager = layoutMan
        listOfPlaces.setHasFixedSize(true)
        listOfPlaces.adapter = adapter
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(listOfPlaces)

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt(Constants.ADAPTER_POSITION)
        }

        listOfPlaces.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
                    position =
                        snapHelper.findSnapView(layoutMan)?.let { layoutMan.getPosition(it) }!!
                loadNext(position)
            }
        })

        initViewModel()
        fetchData()

        MapsInitializer.initialize(this)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initViewModel() {

        val factory: PlaceViewModelFactory =
            Injectors.providePlaceRepository(this.applicationContext)

        placesViewModel = ViewModelProviders.of(this, factory).get(PlacesViewModel::class.java)
        placesViewModel?.let { lifecycle.addObserver(it) }
        placesViewModel?.loadPlacesInDB()
    }

    private fun fetchData() {
        placesViewModel?.fetchPlacesFromDB()!!.observe(this, { places ->

            placesList = ArrayList()

            places?.forEach { place ->
                placesList.add(place)
            }

            adapter.swapData(placesList)


        })
    }

    private fun loadNext(pos: Int) = plotMap(placesList[pos])

    private fun orientationCheck(): Int = when (this.resources.configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> LinearLayout.HORIZONTAL
        else -> LinearLayout.VERTICAL
    }

    private fun loadMarkers() {
        mGoogleMap?.clear()
        for (place in placesList) {
            val location = LatLng(place.latitude, place.longitude)

            mGoogleMap?.addMarker(MarkerOptions().position(location).title(place.place))
        }
    }

    private fun plotMap(placesModel: PlacesModel) {
        val location = LatLng(placesModel.latitude, placesModel.longitude)
        val cameraPosition = CameraPosition.Builder()
            .target(location)
            .zoom(25f)
            .build()

        mGoogleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(Constants.ADAPTER_POSITION, position)
        super.onSaveInstanceState(outState)
    }
}
