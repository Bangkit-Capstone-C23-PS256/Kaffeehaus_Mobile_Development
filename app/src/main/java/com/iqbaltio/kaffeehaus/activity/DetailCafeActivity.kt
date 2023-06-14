package com.iqbaltio.kaffeehaus.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.data.ViewModelFactory
import com.iqbaltio.kaffeehaus.databinding.ActivityDetailCafeBinding
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel

class DetailCafeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCafeBinding
    private val caffeViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCafeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRADATA)


    }

    companion object{
        const val EXTRADATA = "extra_data"
    }
}
