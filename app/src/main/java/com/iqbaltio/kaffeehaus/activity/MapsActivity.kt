package com.iqbaltio.kaffeehaus.activity

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.adapter.CustomInfoAdapter
import com.iqbaltio.kaffeehaus.data.ViewModelFactory
import com.iqbaltio.kaffeehaus.data.api.CafeItem
import com.iqbaltio.kaffeehaus.data.api.RequestSearch
import com.iqbaltio.kaffeehaus.databinding.ActivityMapsBinding
import com.iqbaltio.kaffeehaus.utils.Result
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap :GoogleMap
    private lateinit var binding : ActivityMapsBinding
    private val mainViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }

    companion object {
        private const val TAG = "MapsActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Cafe Location"
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



    }

    private fun setDataMap(){
        val queryUser = intent.getStringExtra("user_input")
        if (queryUser != null){
            mainViewModel.getSearchList(RequestSearch(queryUser)).observe(this){ cafe ->
                when(cafe){
                    is Result.Success -> {
                        makeMarker(cafe.data.search)
                    }
                    is Result.Loading -> {
                        Toast.makeText(this, "Loading....", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Error -> {
                        Toast.makeText(this, cafe.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            mainViewModel.getSearchList(RequestSearch("beautiful cafe with fast wifi")).observe(this){ cafe ->
                when(cafe){
                    is Result.Success -> {
                        makeMarker(cafe.data.search)
                    }
                    is Result.Loading -> {
                        Toast.makeText(this, "Loading....", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Error -> {
                        Toast.makeText(this, cafe.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }


    private fun makeMarker(listCafe : List<CafeItem>){
        for (cafe in listCafe){
            val latlng = LatLng(cafe.latitude, cafe.longitude)
            mMap.setInfoWindowAdapter(CustomInfoAdapter(this, listCafe))
            mMap.addMarker(
                MarkerOptions()
                    .position(latlng)
//                    .snippet(cafe.rating + " || " + cafe.address)
                    .title(cafe.name)
            )
        }
    }

    private fun setMapStyle(){
        try {
            val success =
                mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))
            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (exception: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error : ", exception)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
        setMapStyle()
        setDataMap()
        getMyLocation()
    }

    private fun getMyLocation(){
        if (ContextCompat.checkSelfPermission(this.applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mMap.isMyLocationEnabled = true
        } else {
            requestPermissionLocation.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private val requestPermissionLocation =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if (it) {
                getMyLocation()
            }
        }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}