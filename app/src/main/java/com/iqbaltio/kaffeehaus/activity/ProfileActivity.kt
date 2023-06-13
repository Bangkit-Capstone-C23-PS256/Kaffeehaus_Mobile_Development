package com.iqbaltio.kaffeehaus.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.adapter.PreferensiAdapter
import com.iqbaltio.kaffeehaus.data.ViewModelFactory
import com.iqbaltio.kaffeehaus.databinding.ActivityMainBinding
import com.iqbaltio.kaffeehaus.databinding.ActivityProfileBinding
import com.iqbaltio.kaffeehaus.utils.Result
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var prefAdapter : PreferensiAdapter
    private val loginViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }
    private val preferensiViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPreferensiUser.setHasFixedSize(true)
        binding.rvPreferensiUser.layoutManager = LinearLayoutManager(this)


        loginViewModel.getUser().observe(this){ user ->
            if (user != null){
                if (user.isLogin){
                    val token = user.token
                    val userId = user.id
                    binding.txtNamaUser.text = user.name
                    binding.txtEmailUser.text = user.email
                    preferensiViewModel.getPreferensiUser(token, userId).observe(this){ pref ->
                        when(pref){
                            is Result.Success -> {
                                prefAdapter = PreferensiAdapter(pref.data.preferensiData)
                                binding.rvPreferensiUser.adapter = prefAdapter
                            }
                            is Result.Loading -> {
                                Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                            }
                            is Result.Error -> {
                                Toast.makeText(this, pref.error.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }




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