package com.vi.newsapp.base.adapter

open class BaseDelegationAdapter<T> : MutableDelegationAdapter<T>() {

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(itemCount - 1)
    }

    fun add(items: List<T>) {
        val positionStart = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(positionStart, itemCount)
    }

    fun replace(item: T, position: Int) {
        items[position] = item
        notifyItemChanged(position)
    }

    fun replaceWithPayload(item: T, position: Int, payload: Any) {
        items[position] = item
        notifyItemChanged(position, payload)
    }

    fun remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

}