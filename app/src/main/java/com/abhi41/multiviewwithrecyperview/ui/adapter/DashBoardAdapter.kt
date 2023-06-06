package com.abhi41.multiviewwithrecyperview.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import coil.load
import com.abhi41.multiviewwithrecyperview.R
import com.abhi41.multiviewwithrecyperview.databinding.ChildRecyclerViewBinding
import com.abhi41.multiviewwithrecyperview.databinding.ItemBannerBinding
import com.abhi41.multiviewwithrecyperview.databinding.LayoutExpandableRvBinding
import com.abhi41.multiviewwithrecyperview.databinding.LayoutSliderBinding
import com.abhi41.multiviewwithrecyperview.model.Banner
import com.abhi41.multiviewwithrecyperview.model.DataGrid
import com.abhi41.multiviewwithrecyperview.model.DataItem
import com.abhi41.multiviewwithrecyperview.model.DataItemType
import com.abhi41.multiviewwithrecyperview.model.DataList
import com.abhi41.multiviewwithrecyperview.model.DataSlider
import com.abhi41.multiviewwithrecyperview.model.ExpandableList
import com.abhi41.multiviewwithrecyperview.request.Data
import com.abhi41.multiviewwithrecyperview.request.ExpandableItem
import com.abhi41.multiviewwithrecyperview.ui.screen.AmazonSettingScreen
import com.google.android.material.tabs.TabLayoutMediator

class DashBoardAdapter(
    private val dashBoardList: MutableList<DataItem>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    inner class BannerItemViewHolder(
        private val binding: ItemBannerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindBannerView(banner: Banner) {
            binding.bannerIv.load(banner.image) {
                crossfade(600)
                placeholder(R.drawable.place_holder)
            }
            binding.bannerIv.setOnClickListener {
                val intent = Intent(context,AmazonSettingScreen::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }
                context.startActivity(intent)

            }
        }
    }

    inner class SliderViewHolder(
        private val binding: LayoutSliderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindingSliderView(dataSlider: DataSlider) {
            val adapter = SliderAdapter(dataSlider)
            binding.viewPager2.adapter = adapter

            TabLayoutMediator(binding.intoTabLayout, binding.viewPager2) { tab, pos ->
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

    inner class ExpandableListViewHolder(
        private val binding: LayoutExpandableRvBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindGridItem(items: ExpandableList) {
            val adapter = ExpandableAdapter(items.list)
            binding.rvExpandable.layoutManager =
                GridLayoutManager(context, items.grid?.toInt() ?: 4)
          //  binding.rvExpandable.layoutManager = LinearLayoutManager(context)
            binding.rvExpandable.setHasFixedSize(true)
            adapter.setHasStableIds(true)
            binding.rvExpandable.adapter = adapter

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.item_banner -> {
                val binding =
                    ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BannerItemViewHolder(binding)
            }

            R.layout.layout_slider -> {
                val binding =
                    LayoutSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SliderViewHolder(binding)
            }

            R.layout.item_expandable -> {
                val binding = LayoutExpandableRvBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ExpandableListViewHolder(binding)
            }

            else -> {
                val binding =
                    ChildRecyclerViewBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                binding.childRecyclerView.setRecycledViewPool(viewPool)
                ChildRecyclerViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerItemViewHolder -> {
                dashBoardList[position].banner?.let { holder.bindBannerView(banner = it) }
            }

            is SliderViewHolder -> {
                dashBoardList[position].slider?.let { holder.bindingSliderView(dataSlider = it) }
            }

            is ExpandableListViewHolder -> {
                dashBoardList[position].expandable?.let { holder.bindGridItem(it) }
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
            DataItemType.EXPANDABLE -> R.layout.item_expandable
            else -> R.layout.child_recycler_view
        }
    }

    override fun getItemCount(): Int {
        return dashBoardList.size
    }


}