package com.ragini.navigationapplication.data

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ragini.navigationapplication.model.PlacesModel
import kotlinx.android.synthetic.main.recycler_view.view.*

class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItems(place: PlacesModel) {
        itemView.placeText.text = place.place
        itemView.subText.text = place.address
    }
}