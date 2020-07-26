package br.com.cvaccari.orders.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH: RecyclerView.ViewHolder>(val data: MutableList<T>) :
    RecyclerView.Adapter<VH>() {

    open fun addAll(elements: List<T>) {
        this.data.clear()
        this.data.addAll(elements)
        notifyDataSetChanged()
    }
}