package com.vi.newsapp.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

open class MutableDelegationAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val delegatesManager = AdapterDelegatesManager<MutableList<T>>()
    protected val items: MutableList<T> = arrayListOf()

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(items, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(items, position, holder)
    }

    override fun getItemCount() = items.size

}