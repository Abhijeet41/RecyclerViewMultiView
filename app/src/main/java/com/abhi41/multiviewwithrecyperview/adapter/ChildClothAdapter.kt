package com.abhi41.multiviewwithrecyperview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhi41.multiviewwithrecyperview.adapter.itemType.MainDataItemType
import com.abhi41.multiviewwithrecyperview.databinding.BestSellerLayoutBinding
import com.abhi41.multiviewwithrecyperview.databinding.SingleClothLayoutBinding
import com.abhi41.multiviewwithrecyperview.model.ClothModel

class ChildClothAdapter(
    private val viewType: Int,
    private val rvClothList: List<ClothModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BestSellerViewHolder(private val binding: BestSellerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindBestSellerView(clothItem: ClothModel) {
            binding.bestSellerImage.setImageResource(clothItem.img)
            binding.bestSellerText.text = clothItem.offer
        }

    }

    inner class ClothViewHolder(private val binding: SingleClothLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindClothingView(clothItem: ClothModel) {
            binding.clothingImage.setImageResource(clothItem.img)
            binding.clothingOfferTv.text = clothItem.offer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MainDataItemType.BEST_SELLER -> {
                val binding = BestSellerLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BestSellerViewHolder(binding)
            }

            else -> {
                val binding = SingleClothLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ClothViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is BestSellerViewHolder ->{
                holder.bindBestSellerView(rvClothList[position])
            }

            is ClothViewHolder->{
                holder.bindClothingView(rvClothList[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return rvClothList.size
    }


}