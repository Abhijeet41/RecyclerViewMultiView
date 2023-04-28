package com.abhi41.multiviewwithrecyperview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhi41.multiviewwithrecyperview.R
import com.abhi41.multiviewwithrecyperview.adapter.itemType.MainDataItemType
import com.abhi41.multiviewwithrecyperview.adapter.itemViewHolder.BannerItemViewHolder
import com.abhi41.multiviewwithrecyperview.adapter.itemViewHolder.RecyclerItemViewHolder
import com.abhi41.multiviewwithrecyperview.databinding.BannerItemBinding
import com.abhi41.multiviewwithrecyperview.databinding.ClothesItemBinding
import com.abhi41.multiviewwithrecyperview.model.Banner
import com.abhi41.multiviewwithrecyperview.model.RootAccessories

class AccessoriesAdapter(private val dataItemList: List<RootAccessories>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.banner_item -> {
                val binding =
                    BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BannerItemViewHolder(binding)
            }

            else -> {
                val binding =
                    ClothesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RecyclerItemViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerItemViewHolder -> {
                dataItemList[position].banner?.let {
                    holder.bindBannerView(it)
                }
            }

            else -> {
                when (dataItemList[position].viewType) {
                    MainDataItemType.BEST_SELLER -> {
                        dataItemList[position].clothingList?.let {
                            (holder as RecyclerItemViewHolder).bindBestSellerRecyclerView(it)
                        }
                    }

                    else -> {
                        dataItemList[position].clothingList?.let {
                            (holder as RecyclerItemViewHolder).bindClothingRecyclerView(it)
                        }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataItemList[position].viewType) {
            MainDataItemType.BANNER ->
                R.layout.banner_item

            else ->
                R.layout.clothes_item
        }
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }
}