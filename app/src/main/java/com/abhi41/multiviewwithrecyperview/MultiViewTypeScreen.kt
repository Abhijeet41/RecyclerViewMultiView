package com.abhi41.multiviewwithrecyperview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.abhi41.multiviewwithrecyperview.adapter.AccessoriesAdapter
import com.abhi41.multiviewwithrecyperview.adapter.itemType.MainDataItemType
import com.abhi41.multiviewwithrecyperview.databinding.ActivityMultiViewTypeScreenBinding
import com.abhi41.multiviewwithrecyperview.model.Banner
import com.abhi41.multiviewwithrecyperview.model.ClothModel
import com.abhi41.multiviewwithrecyperview.model.RootAccessories

class MultiViewTypeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMultiViewTypeScreenBinding
    private lateinit var mList: ArrayList<RootAccessories>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultiViewTypeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMain.setHasFixedSize(true)
        binding.rvMain.layoutManager = LinearLayoutManager(this)

        mList = ArrayList()
        prepareData()

        val adapter = AccessoriesAdapter(mList)
        binding.rvMain.adapter = adapter
    }

    private fun prepareData() {
        // best seller
        val bestSellerList = ArrayList<ClothModel>()
        bestSellerList.add(ClothModel(R.drawable.bags , "Up to 20% off"))
        bestSellerList.add(ClothModel(R.drawable.mobiles, "Up to 10% off"))
        bestSellerList.add(ClothModel(R.drawable.watches, "Up to 40% off"))
        bestSellerList.add(ClothModel(R.drawable.bags, "Up to 20% off"))
        bestSellerList.add(ClothModel(R.drawable.mobiles, "Up to 10% off"))
        bestSellerList.add(ClothModel(R.drawable.watches, "Up to 40% off"))

        //clothing
        val clothingList = ArrayList<ClothModel>()
        clothingList.add(ClothModel(R.drawable.levis_clothing, "Up to 25% off"))
        clothingList.add(ClothModel(R.drawable.women_clothing, "Up to 30% off"))
        clothingList.add(ClothModel(R.drawable.nikeshoes, "Up to 35% off"))
        clothingList.add(ClothModel(R.drawable.levis_clothing, "Up to 25% off"))
        clothingList.add(ClothModel(R.drawable.women_clothing, "Up to 30% off"))
        clothingList.add(ClothModel(R.drawable.nikeshoes, "Up to 35% off"))

        mList.add(RootAccessories(MainDataItemType.BEST_SELLER, bestSellerList))
        mList.add(RootAccessories(MainDataItemType.BANNER, Banner(R.drawable.tv_offer)))
        mList.add(RootAccessories(MainDataItemType.CLOTHING, clothingList))
        mList.add(RootAccessories(MainDataItemType.BANNER, Banner(R.drawable.nikon_canon_offer)))
        mList.add(RootAccessories(MainDataItemType.BEST_SELLER, bestSellerList.asReversed()))
        mList.add(RootAccessories(MainDataItemType.BANNER, Banner(R.drawable.offer_shoping)))
        mList.add(RootAccessories(MainDataItemType.BANNER, Banner(R.drawable.nikon_canon_offer)))
        mList.add(RootAccessories(MainDataItemType.CLOTHING, clothingList))

    }
}