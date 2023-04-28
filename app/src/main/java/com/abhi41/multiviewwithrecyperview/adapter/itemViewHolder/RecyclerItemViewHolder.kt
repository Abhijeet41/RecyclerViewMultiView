package com.abhi41.multiviewwithrecyperview.adapter.itemViewHolder

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhi41.multiviewwithrecyperview.adapter.ChildClothAdapter
import com.abhi41.multiviewwithrecyperview.adapter.itemType.MainDataItemType
import com.abhi41.multiviewwithrecyperview.databinding.ClothesItemBinding
import com.abhi41.multiviewwithrecyperview.model.ClothModel

class RecyclerItemViewHolder(private val binding: ClothesItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.rvCloth.setHasFixedSize(true)
        binding.rvCloth.layoutManager =
            LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
    }

    fun bindClothingRecyclerView(recyclerItemList: List<ClothModel>) {
        val adapter = ChildClothAdapter(MainDataItemType.CLOTHING,recyclerItemList)
        binding.rvCloth.adapter = adapter
    }

    fun bindBestSellerRecyclerView(recyclerItemList: List<ClothModel>) {
        val adapter = ChildClothAdapter(MainDataItemType.BEST_SELLER,recyclerItemList)
        binding.rvCloth.adapter = adapter
    }


}