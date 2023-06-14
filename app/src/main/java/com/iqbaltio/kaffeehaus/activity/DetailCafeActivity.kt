package com.iqbaltio.kaffeehaus.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.data.ViewModelFactory
import com.iqbaltio.kaffeehaus.data.api.CafeItem
import com.iqbaltio.kaffeehaus.databinding.ActivityDetailCafeBinding
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel

class DetailCafeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCafeBinding
    private val caffeViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCafeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)
        val urlPhoto = intent.getStringExtra(EXTRA_PHOTO_URL)
        val rating = intent.getStringExtra(EXTRA_RATING)
        val address = intent.getStringExtra(EXTRA_ADDRESS)

        binding.txtTitle.text = name
        Glide.with(this)
            .load(urlPhoto)
            .into(binding.ivDetailCafe)
        binding.txtAddress.text = address

    }


    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PHOTO_URL = "extra_photo"
        const val EXTRA_ADDRESS = "extra_address"
        const val EXTRA_RATING = "extra_rating"
    }
}
