package com.iqbaltio.kaffeehaus.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.adapter.CafeAdapter
import com.iqbaltio.kaffeehaus.data.ViewModelFactory
import com.iqbaltio.kaffeehaus.databinding.ActivityFavoriteBinding
import com.iqbaltio.kaffeehaus.databinding.ActivityProfileBinding
import com.iqbaltio.kaffeehaus.utils.Result
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var cafeAdapter : CafeAdapter
    private val loginViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }
    private val cafeViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.selectedItemId = R.id.navFavorite
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

        binding.rvFavoriteCafe.setHasFixedSize(true)
        binding.rvFavoriteCafe.layoutManager = LinearLayoutManager(this)

        loginViewModel.getUser().observe(this){ user ->
            if (user != null){
                if (user.isLogin){
                    cafeViewModel.getCaffeList().observe(this){ cafe ->
                        when(cafe){
                            is Result.Success -> {
                                cafeAdapter = CafeAdapter(cafe.data.cafe)
                                binding.rvFavoriteCafe.adapter = cafeAdapter
                            }
                            is Result.Loading -> {
                                Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                            }
                            is Result.Error -> {
                                Toast.makeText(this, cafe.error.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navHome -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.navProfile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                    true
                }
                R.id.navFavorite -> {
                    startActivity(Intent(this, FavoriteActivity::class.java))
                    finish()
                    true
                }
                else -> true
            }
        }
    }
}