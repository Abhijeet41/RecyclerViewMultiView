package com.abhi41.multiviewwithrecyperview.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhi41.multiviewwithrecyperview.R
import com.abhi41.multiviewwithrecyperview.databinding.ItemParentAccessoriesBinding
import com.abhi41.multiviewwithrecyperview.model.DataLanguage
import com.abhi41.multiviewwithrecyperview.model.ParentAmazonSetting

class ParentAccessoriesAdapter(
    val context: Context,
    val parentItemList: List<ParentAmazonSetting>
) : RecyclerView.Adapter<ParentAccessoriesAdapter.ParentViewHolder>() {

    inner class ParentViewHolder(val binding: ItemParentAccessoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.childRecyclerView.setHasFixedSize(true)
            binding.childRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            //binding.childRecyclerView.layoutManager = GridLayoutManager(context,2)

            binding.card1.setOnClickListener {
                val parentContent = parentItemList[adapterPosition].parentDataAccessories
                val parentContent2isOpen = parentItemList[adapterPosition].parentDataAccessories2.isOpen
                val upAnim = AnimationUtils.loadAnimation(binding.root.context, R.anim.up)
                val downAnim = AnimationUtils.loadAnimation(binding.root.context, R.anim.down)

                isAnyOpened(adapterPosition)
                parentContent.isOpen =
                    !parentContent.isOpen

                if (parentContent.isOpen){
                    binding.card1.startAnimation(downAnim)
                    binding.recyclerCard.startAnimation(downAnim)
                }else{
                    binding.card1.startAnimation(upAnim)
                }
                if (parentContent2isOpen){
                    binding.card2.startAnimation(upAnim)
                    parentItemList[adapterPosition].parentDataAccessories2.isOpen = false
                }

                notifyItemChanged(adapterPosition,Unit)
            }
            binding.card2.setOnClickListener {
                val parentContent2 = parentItemList[adapterPosition].parentDataAccessories2
                val parentContentOneIsOpen=parentItemList[adapterPosition].parentDataAccessories.isOpen
                val upAnim = AnimationUtils.loadAnimation(binding.root.context, R.anim.up)
                val downAnim = AnimationUtils.loadAnimation(binding.root.context, R.anim.down)
                isAnyOpened(adapterPosition)

                parentContent2.isOpen =
                    !parentContent2.isOpen

                if (parentContent2.isOpen){
                    binding.card2.startAnimation(downAnim)
                    binding.recyclerCard.startAnimation(downAnim)
                }else{
                    binding.card2.startAnimation(upAnim)
                }

                if (parentContentOneIsOpen){
                    binding.card1.startAnimation(upAnim)
                    parentItemList[adapterPosition].parentDataAccessories.isOpen = false
                }
                notifyItemChanged(adapterPosition,Unit)
            }
        }

        private fun isAnyOpened(position: Int){
            if (position != RecyclerView.NO_POSITION){

                var whichItemOpened: Int? = null

                val temp = parentItemList.indexOfFirst {
                    it.parentDataAccessories.isOpen or it.parentDataAccessories2.isOpen
                }

                if (temp >= 0 && temp != position){
                    if(parentItemList[temp].parentDataAccessories.isOpen){
                        parentItemList[temp].parentDataAccessories.isOpen = false
                        whichItemOpened = 0
                    }else if (parentItemList[temp].parentDataAccessories2.isOpen)
                    {
                        parentItemList[temp].parentDataAccessories2.isOpen = false
                        whichItemOpened = 1
                    }

                    notifyItemChanged(temp,whichItemOpened)
                }
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

        fun closeExpandedView(whichItemOpened: Int) {
            val upAnim = AnimationUtils.loadAnimation(binding.root.context, R.anim.up)
            when(whichItemOpened){
                0-> {
                    binding.card1.startAnimation(upAnim)
                }
                1->{
                    binding.card2.startAnimation(upAnim)
                }

            }
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
        if (payloads.isNotEmpty() && ((payloads[0] == 0)or (payloads[0] == 1))){
            holder.closeExpandedView(payloads[0] as Int)
        }else{
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun getItemCount(): Int {
        return parentItemList.size
    }


}