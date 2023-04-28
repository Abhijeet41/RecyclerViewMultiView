package com.abhi41.multiviewwithrecyperview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhi41.multiviewwithrecyperview.R
import com.abhi41.multiviewwithrecyperview.model.ParentModel

class ParentAdapter(
    private val parentList: List<ParentModel>
) : RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logoIv: ImageView = itemView.findViewById(R.id.parentLogo)
        val txtParentTitle: TextView = itemView.findViewById(R.id.txtParentTitle)
        val rv_lang: RecyclerView = itemView.findViewById(R.id.rv_lang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_item, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val parentItem = parentList.get(position)
        holder.logoIv.setImageResource(parentItem.logo)
        holder.txtParentTitle.text = parentItem.title
        holder.rv_lang.setHasFixedSize(true)
        holder.rv_lang.layoutManager = GridLayoutManager(holder.itemView.context, 3)

        val adapter = ChildAdapter(childList = parentItem.mList)
        holder.rv_lang.adapter = adapter
    }

    override fun getItemCount(): Int {
        return parentList.size
    }

}