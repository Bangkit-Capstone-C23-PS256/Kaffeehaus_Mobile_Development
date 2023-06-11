package com.iqbaltio.kaffeehaus.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.activity.LoginActivity
import com.iqbaltio.kaffeehaus.adapter.CafeAdapter
import com.iqbaltio.kaffeehaus.adapter.ImageSliderAdapter
import com.iqbaltio.kaffeehaus.data.CafeData
import com.iqbaltio.kaffeehaus.data.ImageData
import com.iqbaltio.kaffeehaus.data.ViewModelFactory
import com.iqbaltio.kaffeehaus.data.api.CafeItem
import com.iqbaltio.kaffeehaus.databinding.FragmentHomeBinding
import com.iqbaltio.kaffeehaus.databinding.ItemCafeBinding
import com.iqbaltio.kaffeehaus.viewmodel.MainViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ImageSliderAdapter
    private lateinit var adaptercafe: CafeAdapter
    private val cafeList = ArrayList<CafeItem>()
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>
    private val caffeViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(requireContext()) }
    private val loginViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        adaptercafe = CafeAdapter(cafeList)  /* ajg iki di passing opo */
        binding.recyclerView.adapter = adaptercafe

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

        loginViewModel.getUser().observe(viewLifecycleOwner){ user ->
            if (user != null){
                if (user.isLogin){
                    caffeViewModel.getCaffeList().observe(viewLifecycleOwner){
//                        adaptercafe.
                    }
                } else {
                    startActivity(Intent(context, LoginActivity::class.java))
                    activity?.finish()
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
    }

    private fun selectedDots(position: Int) {
        for(i in 0 until list.size){
            if(i == position)
                dots[i].setTextColor(ContextCompat.getColor(requireContext(), R.color.main))
            else
                dots[i].setTextColor(ContextCompat.getColor(requireContext(), R.color.secondary))
        }
    }

    private fun setIndicator() {
        for(i in 0 until list.size){
            dots.add(TextView(context))
            dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()

            dots[i]
            binding.indicator.addView(dots[i])
        }
    }



}