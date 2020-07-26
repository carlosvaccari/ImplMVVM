package br.com.cvaccari.orders.features.previousorders.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.cvaccari.orders.commons.adapters.ItemsAdapter
import br.com.cvaccari.orders.features.models.ItemModel

abstract class PreviousOrdersViewModel : ViewModel() {
    abstract val items: LiveData<List<ItemModel>>
    abstract val itemsAdapter: ItemsAdapter
    abstract fun isLoading(): LiveData<Boolean>
    abstract fun hasError(): LiveData<Boolean>
    abstract fun showSuccessMessage(): LiveData<Boolean>
    abstract fun showRemoveItemError(): LiveData<Boolean>
    abstract fun addItem(item: ItemModel)
    abstract fun removeItem(item: ItemModel)
    abstract fun updateItem(item: ItemModel)
}