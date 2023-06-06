package com.abhi41.multiviewwithrecyperview.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhi41.multiviewwithrecyperview.R
import com.abhi41.multiviewwithrecyperview.model.DataLanguage

class LanguageAdapter(
    private val mLanguageList: List<DataLanguage>
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {
    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.childLogoIv)
        val title: TextView = itemView.findViewById(R.id.childTitleTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_child_languages, parent, false)
        return LanguageViewHolder(view)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.imageView.setImageResource(mLanguageList[position].image)
        holder.title.text = mLanguageList[position].title
    }

    override fun getItemCount(): Int {
        return mLanguageList.size
    }
}