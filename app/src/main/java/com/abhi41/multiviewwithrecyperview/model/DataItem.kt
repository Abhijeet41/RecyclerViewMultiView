package com.abhi41.multiviewwithrecyperview.model

import com.abhi41.multiviewwithrecyperview.request.Item
import com.abhi41.multiviewwithrecyperview.request.SliderItem

data class DataItem(val viewTye: Int) {
    var banner: Banner? = null
    var dataList: DataList? = null
    var dataGrid: DataGrid? = null
    var slider: DataSlider? = null
    constructor(viewTye: Int, banner: Banner) : this(viewTye) {
        this.banner = banner
    }
    constructor(viewTye: Int, dataList: DataList) : this(viewTye) {
        this.dataList = dataList
    }
    constructor(viewTye: Int, dataGrid: DataGrid) : this(viewTye) {
        this.dataGrid = dataGrid
    }
    constructor(viewTye: Int,dataSlider: DataSlider): this(viewTye){
        this.slider = dataSlider
    }

}


data class Banner(val image: String)
data class DataList(val list: ArrayList<Item>)
data class DataGrid(val category: String?, val grid: String?, val list: ArrayList<Item>)
data class DataSlider(val list: ArrayList<SliderItem>)