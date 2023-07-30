package com.iqbaltio.kaffeehaus.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.adapter.CafeAdapter
import com.iqbaltio.kaffeehaus.adapter.ImageSliderAdapter
import com.iqbaltio.kaffeehaus.data.ImageData
import com.iqbaltio.kaffeehaus.data.ViewModelFactory
import com.iqbaltio.kaffeehaus.data.api.RequestSearch
import com.iqbaltio.kaffeehaus.databinding.ActivityMainBinding
import com.iqbaltio.kaffeehaus.utils.Result
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel
import kotlinx.coroutines.Job

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageSliderAdapter
    private lateinit var adaptercafe: CafeAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var queryUser : String
    private lateinit var dots: ArrayList<TextView>
    private val caffeViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }
    private val loginViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.getUser().observe(this){ user ->
            if (user != null){
                if (user.isLogin){
                    binding.txtName.text = user.name
                    Log.d("SUKSES", user.toString())
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    Log.d("GAGAL", "GAGAL")
                }
            }
        }

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

//        caffeViewModel.isLoading.observe(this, ::showLoading)

        list.add(
            ImageData(
                "https://images.unsplash.com/photo-1499750310107-5fef28a66643?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
            )
        )

        list.add(
            ImageData(
                "https://images.unsplash.com/photo-1516062423079-7ca13cdc7f5a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=883&q=80"
            )
        )

        list.add(
            ImageData(
                "https://images.unsplash.com/photo-1456324463128-7ff6903988d8?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80"
            )
        )


        binding.searchViewInput.setOnKeyListener(View.OnKeyListener{ _, keyCode, event ->

            queryUser = binding.searchViewInput.text.toString()

            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){

                binding.progressBar.visibility = View.VISIBLE
                caffeViewModel.getSearchList(RequestSearch(queryUser)).observe(this){ cafe ->
                    when(cafe){
                        is Result.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.searchViewInput.text!!.clear()
                            Toast.makeText(this, "Searching cafe with ${queryUser} keyword....", Toast.LENGTH_SHORT).show()
                            adaptercafe = CafeAdapter(cafe.data.search)
                            binding.recyclerView.adapter = adaptercafe
                        }
                        is Result.Loading -> {
                            Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                        }
                        is Result.Error -> {
                            Toast.makeText(this, "The Query must be in english \n${cafe.error}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                return@OnKeyListener true
            }
            false
        })



        loginViewModel.getUser().observe(this){ user ->
            if (user != null){
                if (user.isLogin){
                    binding.progressBar.visibility = View.VISIBLE
                    caffeViewModel.getCaffeList().observe(this){
                        when(it){
                            is Result.Success -> {
                                binding.progressBar.visibility = View.GONE
                                adaptercafe = CafeAdapter(it.data.cafe)
                                binding.recyclerView.adapter = adaptercafe
                            }
                            is Result.Loading -> {
                                Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
                            }
                            is Result.Error -> {
                                Toast.makeText(this, it.error.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }

        adapter = ImageSliderAdapter(list)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDots(position)
                super.onPageSelected(position)
            }
        })

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

        binding.fab.setOnClickListener {
            queryUser = ""
            if (queryUser.isNotEmpty()){
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("user_input", queryUser)
                startActivity(intent)
            }else{
                val intent = Intent(this, MapsActivity::class.java)
                startActivity(intent)
            }

        }

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
    }

//    private fun showLoading(isLoading: Boolean) {
//        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
//    }

    private fun selectedDots(position: Int) {
        for(i in 0 until list.size){
            if(i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.main))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.secondary))
        }
    }

    private fun setIndicator() {
        for(i in 0 until list.size){
            dots.add(TextView(this))
            dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()

            dots[i]
            binding.indicator.addView(dots[i])
        }
    }

}