package com.abhi41.multiviewwithrecyperview.adapter.itemViewHolder

import androidx.recyclerview.widget.RecyclerView
import com.abhi41.multiviewwithrecyperview.databinding.BannerItemBinding
import com.abhi41.multiviewwithrecyperview.model.Banner

class BannerItemViewHolder(
    private val binding: BannerItemBinding
): RecyclerView.ViewHolder(binding.root){

    fun bindBannerView(banner: Banner){
        binding.imgBanner.setImageResource(banner.img)
    }
}