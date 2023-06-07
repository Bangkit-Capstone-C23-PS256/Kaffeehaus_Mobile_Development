package com.iqbaltio.kaffeehaus.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        loginViewModel.getUser().observe(this){ user ->
            if (user != null){
                if (user.isLogin){
                    Log.d("SUKSES", user.toString())
                    Toast.makeText(this, "Welcome ${user.name}", Toast.LENGTH_SHORT).show()
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    Log.d("GAGAL", "GAGAL")
                }
            }
        }

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

        binding.fab.setOnClickListener {
            loginViewModel.logoutUser()
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