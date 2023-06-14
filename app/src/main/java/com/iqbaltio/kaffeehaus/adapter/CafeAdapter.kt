package com.iqbaltio.kaffeehaus.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iqbaltio.kaffeehaus.activity.DetailCafeActivity
import com.iqbaltio.kaffeehaus.activity.QuizActivity
import com.iqbaltio.kaffeehaus.data.api.CafeItem
import com.iqbaltio.kaffeehaus.databinding.ItemCafeBinding

class CafeAdapter(private val cafeList : List<CafeItem>) : RecyclerView.Adapter<CafeAdapter.CafeViewHolder>(){

    inner class CafeViewHolder(itemView : ItemCafeBinding) : RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView
        fun bind(data : CafeItem) {
            with(binding) {
                binding.txtTitle.text = data.name
                binding.txtAddress.text = data.address
                binding.txtRating.text = data.rating
                Glide.with(itemView)
                    .load(data.urlPhoto)
                    .into(ivCafe)
                binding.fabCafe.setOnClickListener {
                    val acontext = binding.fabCafe.context
                    val sendName = data.name
                    val pindah = Intent(acontext, DetailCafeActivity::class.java)
                    pindah.putExtra(DetailCafeActivity.EXTRADATA, sendName)
                    acontext.startActivity(pindah)
                }
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