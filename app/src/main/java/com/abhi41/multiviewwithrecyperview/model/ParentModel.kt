package com.abhi41.multiviewwithrecyperview.model

data class ParentModel(
    val title:String,
    val logo: Int,
    val mList:List<ChildModel>
)
