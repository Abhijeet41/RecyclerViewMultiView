package com.abhi41.multiviewwithrecyperview.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhi41.multiviewwithrecyperview.databinding.ItemParentAccessoriesBinding
import com.abhi41.multiviewwithrecyperview.model.DataAccessories
import com.abhi41.multiviewwithrecyperview.model.DataLanguage
import com.abhi41.multiviewwithrecyperview.model.ParentAmazonSetting

class ParentAccessoriesAdapter(
    val parentItemList: List<ParentAmazonSetting>
) : RecyclerView.Adapter<ParentAccessoriesAdapter.ParentViewHolder>() {

    inner class ParentViewHolder(val binding: ItemParentAccessoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.childRecyclerView.setHasFixedSize(true)
            binding.childRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)

            binding.card1.setOnClickListener {
                val parentContent = parentItemList[adapterPosition].parentDataAccessories
                isAnyOpened(adapterPosition)
                parentContent.isOpen =
                    !parentContent.isOpen
                parentItemList[adapterPosition].parentDataAccessories2.isOpen = false
                notifyItemChanged(adapterPosition)
            }
            binding.card2.setOnClickListener {
                val parentContent = parentItemList[adapterPosition].parentDataAccessories2
                isAnyOpened(adapterPosition)
                parentContent.isOpen =
                    !parentContent.isOpen
                parentItemList[adapterPosition].parentDataAccessories.isOpen = false
                notifyItemChanged(adapterPosition)
            }
        }

        private fun isAnyOpened(position: Int){
            val temp = parentItemList.indexOfFirst {
                it.parentDataAccessories.isOpen or it.parentDataAccessories2.isOpen
            }
            if (temp >= 0 && temp != position){
                parentItemList[temp].parentDataAccessories.isOpen = false
                parentItemList[temp].parentDataAccessories2.isOpen = false
                notifyItemChanged(temp,10)
            }
        }

        private fun setChildRecyclerView(childItemList: List<DataLanguage>) {
            binding.recyclerCard.visibility = View.VISIBLE
            val adapter = LanguageAdapter(childItemList)
            binding.childRecyclerView.adapter = adapter
        }

        fun bind(parentItem: ParentAmazonSetting) {
            binding.parentTv.text = parentItem.parentDataAccessories.title
            binding.parentIv.setImageResource(parentItem.parentDataAccessories.image)
            binding.parent2Tv.text = parentItem.parentDataAccessories2.title
            binding.parent2Iv.setImageResource(parentItem.parentDataAccessories2.image)

            when (true) {
                parentItem.parentDataAccessories.isOpen -> {
                    setChildRecyclerView(parentItem.parentDataAccessories.childItemList)
                }

                parentItem.parentDataAccessories2.isOpen -> {
                    setChildRecyclerView(parentItem.parentDataAccessories2.childItemList)
                }

                else -> {
                    binding.recyclerCard.visibility = View.GONE
                }
            }

        }

        fun closeExpandedView() {
            binding.recyclerCard.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val binding =
            ItemParentAccessoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(parentItemList[position])
    }

    override fun onBindViewHolder(
        holder: ParentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty() && payloads[0] == 10){
            holder.closeExpandedView()
        }else{
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun getItemCount(): Int {
        return parentItemList.size
    }


}