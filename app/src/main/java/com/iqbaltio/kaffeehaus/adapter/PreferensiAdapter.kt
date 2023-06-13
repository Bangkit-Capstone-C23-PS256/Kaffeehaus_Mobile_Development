package com.iqbaltio.kaffeehaus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iqbaltio.kaffeehaus.data.api.PreferensiDataItem
import com.iqbaltio.kaffeehaus.databinding.ItemPreferensiBinding

class PreferensiAdapter(private val pref : List<PreferensiDataItem>) : RecyclerView.Adapter<PreferensiAdapter.ViewHolder>() {

    private lateinit var onItemCallback : OnItemClickCallback
    private lateinit var itemPreferensiBinding : ItemPreferensiBinding

    class ViewHolder(var itemPreferensiBinding: ItemPreferensiBinding) :
            RecyclerView.ViewHolder(itemPreferensiBinding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data : PreferensiDataItem)
    }

    fun setOnItemClickCallback(onItemCallback: OnItemClickCallback) {
        this.onItemCallback = onItemCallback
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreferensiAdapter.ViewHolder {
        itemPreferensiBinding = ItemPreferensiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemPreferensiBinding)
    }

    override fun onBindViewHolder(holder: PreferensiAdapter.ViewHolder, position: Int) {
        val (view, ambience, utils) = pref[position]
        holder.itemPreferensiBinding.tvOptionView.text = view
        holder.itemPreferensiBinding.tvOptionAmbience.text = ambience
        holder.itemPreferensiBinding.tvOptionUtils.text = utils
        holder.itemPreferensiBinding.btnSearchPref.setOnClickListener { onItemCallback.onItemClicked(pref[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = pref.size
}