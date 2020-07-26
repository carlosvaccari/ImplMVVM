package br.com.cvaccari.orders.commons.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.com.cvaccari.orders.BR
import br.com.cvaccari.orders.R
import br.com.cvaccari.orders.base.BaseAdapter
import br.com.cvaccari.orders.features.models.ItemModel
import br.com.cvaccari.orders.features.previousorders.viewmodel.PreviousOrdersViewModel

class ItemsAdapter(val viewModel: PreviousOrdersViewModel) : BaseAdapter<ItemModel, ItemsAdapter.ItemViewHolder>(mutableListOf()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_order,
                parent,
                false
            )
        val holder = ItemViewHolder(binding)
        binding.lifecycleOwner = parent.findFragment()
        return holder
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position], viewModel)
    }

    class ItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            itemModel: ItemModel,
            viewModel: PreviousOrdersViewModel
        ) {
            binding.root.findViewById<TextView>(R.id.textview_item_previous_price).paintFlags =
                Paint.STRIKE_THRU_TEXT_FLAG
            binding.setVariable(BR.itemModel, itemModel)
            binding.setVariable(BR.viewModel, viewModel)
            binding.executePendingBindings()
        }

    }
}