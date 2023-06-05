package com.abhi41.multiviewwithrecyperview.ui.screen

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abhi41.multiviewwithrecyperview.model.Banner
import com.abhi41.multiviewwithrecyperview.model.DataGrid
import com.abhi41.multiviewwithrecyperview.model.DataItem
import com.abhi41.multiviewwithrecyperview.model.DataItemType
import com.abhi41.multiviewwithrecyperview.model.DataList
import com.abhi41.multiviewwithrecyperview.model.DataSlider
import com.abhi41.multiviewwithrecyperview.model.ExpandableList
import com.abhi41.multiviewwithrecyperview.request.RequestData
import com.abhi41.multiviewwithrecyperview.util.AppConstants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class DashBoardViewModel(
    val context: Application
) : AndroidViewModel(context) {
    private val TAG = "DashBoardViewModel"

    val dashBoardListData = MutableLiveData<MutableList<DataItem>>()

    init {
        fetchDashBoardData()
    }

    private fun fetchDashBoardData() {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("dashboard_data.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.e(TAG, ioException.toString())
        }

        val listItemType = object : TypeToken<RequestData>() {}.type
        val requestData: RequestData = Gson().fromJson(jsonString, listItemType)

        val recyclerViewList = mutableListOf<DataItem>()

        requestData.data.map { data ->
            when (data.type) {
                AppConstants.typeBanner -> {
                    recyclerViewList.add(
                        DataItem(viewTye = DataItemType.BANNER, Banner(image = data.image ?: ""))
                    )
                }
                AppConstants.typeDataSlider ->{
                    recyclerViewList.add(
                        DataItem(viewTye = DataItemType.SLIDER, DataSlider(list = data.slider))
                    )
                }
                AppConstants.typeDataList -> {
                    recyclerViewList.add(
                        DataItem(viewTye = DataItemType.LIST, DataList(list = data.list))
                    )
                }
                AppConstants.typeExpandableRv ->{
                    recyclerViewList.add(
                        DataItem(viewTye = DataItemType.EXPANDABLE, ExpandableList(list = data.expandable, grid = data.grid))
                    )
                }
                else -> {
                    recyclerViewList.add(
                        DataItem(
                            viewTye = DataItemType.GRID,
                            DataGrid(category = data.category, grid = data.grid, list = data.list)
                        )
                    )
                }

            }
        }

        dashBoardListData.postValue(recyclerViewList)

    }


}