package com.abhi41.multiviewwithrecyperview.model

data class RootAccessories(
   val viewType: Int
){
    var clothingList: List<ClothModel>? = null
    var banner: Banner? = null

    constructor(viewType: Int, clothingList: List<ClothModel>) : this(viewType){
        this.clothingList = clothingList
    }

    constructor(viewType: Int, banner: Banner): this(viewType){
        this.banner = banner
    }

}
