package com.abhi41.multiviewwithrecyperview.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.abhi41.multiviewwithrecyperview.R
import com.abhi41.multiviewwithrecyperview.databinding.ChildRecyclerViewBinding
import com.abhi41.multiviewwithrecyperview.databinding.ItemBannerBinding
import com.abhi41.multiviewwithrecyperview.databinding.LayoutSliderBinding
import com.abhi41.multiviewwithrecyperview.model.Banner
import com.abhi41.multiviewwithrecyperview.model.DataGrid
import com.abhi41.multiviewwithrecyperview.model.DataItem
import com.abhi41.multiviewwithrecyperview.model.DataItemType
import com.abhi41.multiviewwithrecyperview.model.DataList
import com.abhi41.multiviewwithrecyperview.model.DataSlider
import com.google.android.material.tabs.TabLayoutMediator

class DashBoardAdapter(
    private val dashBoardList: MutableList<DataItem>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BannerItemViewHolder(
        private val binding: ItemBannerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindBannerView(banner: Banner) {
            binding.bannerIv.load(banner.image) {
                crossfade(600)
                placeholder(R.drawable.place_holder)
            }
        }
    }

    inner class SliderViewHolder(
        private val binding: LayoutSliderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindingSliderView(dataSlider: DataSlider){
            val adapter = SliderAdapter(dataSlider)
            binding.viewPager2.adapter = adapter

            TabLayoutMediator(binding.intoTabLayout,binding.viewPager2){tab,pos->
            }.attach()
        }

    }

    inner class ChildRecyclerViewHolder(
        private val binding: ChildRecyclerViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindListRecyclerview(dataList: DataList) {
            val adapter = ChildAdapter(viewType = DataItemType.LIST, itemList = dataList.list)
            binding.childRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.childRecyclerView.adapter = adapter
        }

        fun bindGridRecyclerview(dataGrid: DataGrid) {
            val adapter = ChildAdapter(viewType = DataItemType.GRID, itemList = dataGrid.list)
            binding.childRecyclerView.layoutManager =
                dataGrid.grid?.toInt()?.let { GridLayoutManager(context, it) }
            binding.childRecyclerView.adapter = adapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_banner -> {
                val binding =
                    ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BannerItemViewHolder(binding)
            }
            R.layout.layout_slider ->{
                val binding = LayoutSliderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                SliderViewHolder(binding)
            }
            else -> {
                val binding =
                    ChildRecyclerViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ChildRecyclerViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerItemViewHolder -> {
                dashBoardList[position].banner?.let { holder.bindBannerView(banner = it) }
            }
            is SliderViewHolder ->{
                dashBoardList[position].slider?.let { holder.bindingSliderView(dataSlider = it) }
            }
            is ChildRecyclerViewHolder -> {
                when (dashBoardList[position].viewTye) {
                    DataItemType.BANNER -> {
                        dashBoardList[position].dataList?.let { holder.bindListRecyclerview(dataList = it) }
                    }

                    DataItemType.GRID -> {
                        dashBoardList[position].dataGrid?.let { holder.bindGridRecyclerview(dataGrid = it) }
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dashBoardList[position].viewTye) {
            DataItemType.BANNER -> R.layout.item_banner
            DataItemType.SLIDER -> R.layout.layout_slider
            else -> R.layout.child_recycler_view
        }
    }

    override fun getItemCount(): Int {
        return dashBoardList.size
    }


}