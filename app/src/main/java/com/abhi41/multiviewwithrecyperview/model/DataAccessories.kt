package com.abhi41.multiviewwithrecyperview.model


data class ParentAmazonSetting(
    val parentDataAccessories: DataAccessories,
    val parentDataAccessories2: DataAccessories
)

data class DataAccessories (
    val image: Int,
    val title: String,
    val childItemList: List<DataLanguage>,
    var isOpen: Boolean = false
)
data class DataLanguage(val title : String , val image : Int)