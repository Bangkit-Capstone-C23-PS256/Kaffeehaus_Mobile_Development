package com.iqbaltio.kaffeehaus.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.data.ViewModelFactory
import com.iqbaltio.kaffeehaus.databinding.ActivityMainBinding
import com.iqbaltio.kaffeehaus.fragment.FavoriteFragment
import com.iqbaltio.kaffeehaus.fragment.HomeFragment
import com.iqbaltio.kaffeehaus.fragment.ProfileFragment
import com.iqbaltio.kaffeehaus.fragment.SearchFragment
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val loginViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.navHome ->{
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navFavorite ->{
                    loadFragment(FavoriteFragment())
                    true
                }
                R.id.navSearch ->{
                    loadFragment(SearchFragment())
                    true
                }
                R.id.navProfile ->{
                    loadFragment(ProfileFragment())
                    true
                }
                else -> true
            }
        }

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}