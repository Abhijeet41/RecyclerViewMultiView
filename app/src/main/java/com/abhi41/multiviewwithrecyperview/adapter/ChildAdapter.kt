package com.abhi41.multiviewwithrecyperview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhi41.multiviewwithrecyperview.R
import com.abhi41.multiviewwithrecyperview.model.ChildModel

class ChildAdapter (private val childList: List<ChildModel>): RecyclerView.Adapter<ChildAdapter.ChildItemView>() {

    inner class ChildItemView(itemView: View): RecyclerView.ViewHolder(itemView) {
        val logoIv: ImageView = itemView.findViewById(R.id.childLogoIv)
        val childTitleTv: TextView = itemView.findViewById(R.id.childTitleTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildItemView {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.child_item,parent,false)
        return ChildItemView(view)
    }

    override fun onBindViewHolder(holder: ChildItemView, position: Int) {

        holder.logoIv.setImageResource(childList.get(position).logo)
        holder.childTitleTv.text = childList.get(position).title
    }

    override fun getItemCount(): Int {
        return childList.size
    }
}