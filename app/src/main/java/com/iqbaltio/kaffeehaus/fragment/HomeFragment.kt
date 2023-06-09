package com.iqbaltio.kaffeehaus.fragment

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.adapter.CafeAdapter
import com.iqbaltio.kaffeehaus.adapter.ImageSliderAdapter
import com.iqbaltio.kaffeehaus.data.CafeData
import com.iqbaltio.kaffeehaus.data.ImageData
import com.iqbaltio.kaffeehaus.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ImageSliderAdapter
    private lateinit var adaptercafe: CafeAdapter
    private val list = ArrayList<ImageData>()
    private val cafelist = ArrayList<CafeData>()
    private lateinit var dots: ArrayList<TextView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cafelist.add(
            CafeData(
                "https://www.malangculinary.com/upload/img_1616732865.jpg",
                "DW Coffee Shop",
                "Jl. Bogor No.11, Sumbersari, Kec. Lowokwaru, Kota Malang",
                "4.4"
            )
        )

        cafelist.add(
            CafeData(
                "https://www.malangculinary.com/upload/img_1616732865.jpg",
                "DW Coffee Shop",
                "Jl. Bogor No.11, Sumbersari, Kec. Lowokwaru, Kota Malang",
                "4.4"
            )
        )

        cafelist.add(
            CafeData(
                "https://www.malangculinary.com/upload/img_1616732865.jpg",
                "DW Coffee Shop",
                "Jl. Bogor No.11, Sumbersari, Kec. Lowokwaru, Kota Malang",
                "4.4"
            )
        )

        cafelist.add(
            CafeData(
                "https://www.malangculinary.com/upload/img_1616732865.jpg",
                "DW Coffee Shop",
                "Jl. Bogor No.11, Sumbersari, Kec. Lowokwaru, Kota Malang",
                "4.4"
            )
        )

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        adaptercafe = CafeAdapter(cafelist)
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