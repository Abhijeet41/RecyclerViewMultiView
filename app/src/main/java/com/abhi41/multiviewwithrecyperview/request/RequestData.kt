package com.abhi41.multiviewwithrecyperview.request

import com.google.gson.annotations.SerializedName



data class RequestData(
    @SerializedName("data") var data: ArrayList<Data> = arrayListOf()
)


data class Data(
    @SerializedName("type") var type: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("grid") var grid: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("list") var list: ArrayList<Item> = arrayListOf(),
    @SerializedName("slides") var slider: ArrayList<SliderItem> = arrayListOf(),
    @SerializedName("expandable") var expandable: ArrayList<ExpandableItem> = arrayListOf(),

)

data class Item(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("value")
    val value: String? = null
)

data class SliderItem(
    @SerializedName("image")
    val image: String? = null
)

data class ExpandableItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("description")
    val description: String? = null,
    var isExpandable: Boolean = false
)