package com.abhi41.multiviewwithrecyperview.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.abhi41.multiviewwithrecyperview.R
import com.abhi41.multiviewwithrecyperview.databinding.ItemGridBinding
import com.abhi41.multiviewwithrecyperview.databinding.ItemListBinding
import com.abhi41.multiviewwithrecyperview.model.DataItemType
import com.abhi41.multiviewwithrecyperview.request.Item

class ChildAdapter(
    private val viewType: Int,
    private val itemList: MutableList<Item>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
      return when (viewType) {
            DataItemType.LIST -> {
                val binding = ItemListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                ListViewHolder(binding)
            }
            else -> {
                val binding = ItemGridBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                GridViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ListViewHolder -> {
                holder.bindListView(itemList[position])
            }
            is GridViewHolder ->{
                holder.bindGridItem(itemList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ListViewHolder(
        private val binding: ItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindListView(item: Item) {
            binding.txtName.text = item.name
            binding.txtValue.text = item.value
            binding.txtDescription.text = item.description
            binding.imageView.load(item.image) {
                crossfade(600)
                placeholder(R.drawable.place_holder)
            }
        }
    }

    inner class GridViewHolder(
        private val binding: ItemGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindGridItem(item: Item) {
            binding.imageView.load(item.image) {
                crossfade(600)
                placeholder(R.drawable.place_holder)
            }
        }
    }


}