package com.abhi41.multiviewwithrecyperview.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.abhi41.multiviewwithrecyperview.R
import com.abhi41.multiviewwithrecyperview.databinding.ItemExpandableBinding
import com.abhi41.multiviewwithrecyperview.request.ExpandableItem

class ExpandableAdapter(
    val expandableList: List<ExpandableItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemExpandableBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ExpandableAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExpandableAdapterViewHolder ->
                holder.bindGridItem(expandableList[position])
        }
    }

    override fun getItemCount(): Int {
        return expandableList.size
    }

    override fun getItemId(position: Int): Long {

        return expandableList[position].id.toLong()
    }

    inner class ExpandableAdapterViewHolder(
        private val binding: ItemExpandableBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindGridItem(item: ExpandableItem) {

            binding.txtLaptopName.text = item.name
            binding.imgLaptop.load(item.image) {
                crossfade(600)
                placeholder(R.drawable.place_holder)
            }
            binding.txtDescription.text = item.description
            val isExpandable = item.isExpandable

           binding.txtDescription.visibility = if (isExpandable) View.VISIBLE else View.GONE

            binding.childConstrant.setOnClickListener {
                isAnyItemExpanded(adapterPosition)
                item.isExpandable = !item.isExpandable
                //notifyItemChanged(adapterPosition)
                notifyDataSetChanged()
            }
        }

        fun collapsedExpandedView() {
            binding.txtDescription.visibility = View.GONE
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty() && payloads[0] == 0) {
            when (holder) {
                is ExpandableAdapterViewHolder -> holder.collapsedExpandedView()
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }
    //this basically other items will collapsed when new item is expanded
    private fun isAnyItemExpanded(adapterPosition: Int) {
        val expandedViewPos = expandableList.indexOfFirst {
            it.isExpandable
        }
        //above temp return value of any item which has expandable true
        if (expandedViewPos >= 0 && expandedViewPos != adapterPosition) {
            expandableList[expandedViewPos].isExpandable = false
            notifyItemChanged(expandedViewPos,0)
           //but animation sucks
        }
    }
}
