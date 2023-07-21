package com.iqbaltio.kaffeehaus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.material.imageview.ShapeableImageView
import com.iqbaltio.kaffeehaus.data.api.CafeItem
import com.iqbaltio.kaffeehaus.R

class CustomInfoAdapter(private val context: Context, private val cafeList : List<CafeItem>) : GoogleMap.InfoWindowAdapter {
    override fun getInfoContents(marker: Marker): View? {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_info_marker, null)

        val customInfoMarkerImage = view.findViewById<ShapeableImageView>(R.id.iv_cafe)
        val customInfoMarkerName = view.findViewById<TextView>(R.id.txtTitle)
        val customInfoMarkerAddress = view.findViewById<TextView>(R.id.txtAddress)
        val customInfoMarkerRating = view.findViewById<TextView>(R.id.txtRating)

        // Retrieve data from the ArrayList based on the marker's title
        val markerData = cafeList.find { it.name == marker.title }
        markerData?.let {
            customInfoMarkerName.text = it.name
            customInfoMarkerAddress.text = it.address
            customInfoMarkerRating.text = it.rating
            Glide.with(context)
                .load(it.urlPhoto)
                .placeholder(R.drawable.placeholderimagecafe)
                .into(customInfoMarkerImage)
        }

        return view
    }

    override fun getInfoWindow(p0: Marker): View? {
        return null
    }
}