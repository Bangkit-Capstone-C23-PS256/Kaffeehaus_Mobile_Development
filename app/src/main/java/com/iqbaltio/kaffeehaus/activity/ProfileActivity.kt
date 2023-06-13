package com.iqbaltio.kaffeehaus.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.databinding.ActivityMainBinding
import com.iqbaltio.kaffeehaus.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.selectedItemId = R.id.navProfile
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false

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