package com.ragini.navigationapplication.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ragini.navigationapplication.R
import com.ragini.navigationapplication.model.PlacesModel

class PlaceAdapter(private var places: List<PlacesModel>) :
    RecyclerView.Adapter<PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        return PlaceViewHolder(v)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bindItems(places.get(position))
    }

    override fun getItemCount(): Int {
        return places.size
    }

    fun swapData(PlacesNew: List<PlacesModel>) {
        if (places != PlacesNew) {
            places = PlacesNew
            notifyDataSetChanged()
        }
    }
}