package com.iqbaltio.kaffeehaus.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iqbaltio.kaffeehaus.R
import com.iqbaltio.kaffeehaus.data.CafeData
import com.iqbaltio.kaffeehaus.data.ImageData
import com.iqbaltio.kaffeehaus.databinding.ItemCafeBinding
import com.iqbaltio.kaffeehaus.databinding.ItemSlideBinding

class CafeAdapter(private val cafeList: ArrayList<CafeData>) : RecyclerView.Adapter<CafeAdapter.CafeViewHolder>(){

    inner class CafeViewHolder(itemView : ItemCafeBinding) : RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView
        fun bind(data : CafeData) {
            with(binding) {
                binding.txtTitle.text = data.title
                binding.txtAddress.text = data.address
                binding.txtRating.text = data.rating
                Glide.with(itemView)
                    .load(data.imageUrl)
                    .into(ivCafe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        return CafeViewHolder(ItemCafeBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
        holder.bind(cafeList[position])
    }

    override fun getItemCount(): Int = cafeList.size
}