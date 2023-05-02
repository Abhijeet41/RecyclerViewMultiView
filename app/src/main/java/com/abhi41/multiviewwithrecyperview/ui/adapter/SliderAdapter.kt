package com.abhi41.multiviewwithrecyperview.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.abhi41.multiviewwithrecyperview.R
import com.abhi41.multiviewwithrecyperview.databinding.ItemSliderBinding
import com.abhi41.multiviewwithrecyperview.model.DataSlider
import com.abhi41.multiviewwithrecyperview.request.SliderItem

class SliderAdapter(
    private val dataSlider: DataSlider
) : RecyclerView.Adapter<SliderAdapter.SliderAdapterHolder>() {

    inner class SliderAdapterHolder(val binding: ItemSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindSlider(siderItem: SliderItem) {
            binding.imgBanner.load(siderItem.image) {
                crossfade(600)
                placeholder(R.drawable.place_holder)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderAdapterHolder {
        val binding = ItemSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderAdapterHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderAdapterHolder, position: Int) {
        val sliderItem = dataSlider.list
        holder.bindSlider(sliderItem[position])
    }
    override fun getItemCount(): Int {
        return dataSlider.list.size
    }



}