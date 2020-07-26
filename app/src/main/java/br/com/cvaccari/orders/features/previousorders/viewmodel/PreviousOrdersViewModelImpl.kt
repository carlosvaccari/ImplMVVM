package br.com.cvaccari.orders.features.previousorders.viewmodel

import androidx.lifecycle.*
import br.com.cvaccari.orders.commons.adapters.ItemsAdapter
import br.com.cvaccari.orders.features.models.ItemModel
import br.com.cvaccari.orders.usecases.GetPreviousOrdersUseCase
import br.com.cvaccari.orders.usecases.UpdatePreviousOrdersUseCase
import kotlinx.coroutines.launch

class PreviousOrdersViewModelImpl(
    private val getPreviousOrdersUseCase: GetPreviousOrdersUseCase,
    private val updatePreviousOrdersUseCase: UpdatePreviousOrdersUseCase
) : PreviousOrdersViewModel() {

    private val loading: MutableLiveData<Boolean> = MutableLiveData(true)

    private val showMessage: MutableLiveData<Boolean> = MutableLiveData(false)

    private val removeItemError: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun showRemoveItemError(): LiveData<Boolean> = Transformations.map(removeItemError) {
        removeItemError.value ?: false
    }

    override fun showSuccessMessage(): LiveData<Boolean> = Transformations.map(showMessage) {
        showMessage.value ?: false
    }

    override fun isLoading(): LiveData<Boolean> = Transformations.map(loading) {
        loading.value ?: false
    }

    override fun hasError(): LiveData<Boolean> = Transformations.map(items) {
        it.isEmpty()
    }

    override val items: LiveData<List<ItemModel>> = liveData {
        emitSource(
            getPreviousOrdersUseCase(Unit)
                .asLiveData(viewModelScope.coroutineContext)
        ).also {
            hideLoading()
        }
    }

    override val itemsAdapter = ItemsAdapter(this)

    override fun addItem(item: ItemModel) {
        items.value?.apply {
            this[indexOf(item)].itemCount++
            itemsAdapter.notifyDataSetChanged()
        }
    }

    override fun removeItem(item: ItemModel) {
        items.value?.apply {
            if(this[indexOf(item)].itemCount == 0) {
                removeItemError()
            } else {
                this[indexOf(item)].itemCount--
                itemsAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun updateItem(item: ItemModel) {
        showLoading()
        viewModelScope.launch {
            updatePreviousOrdersUseCase(item)
        }.also {
            hideLoading()
            showMessage()
        }
    }

    private fun hideLoading() {
        loading.postValue(false)
    }

    private fun showLoading() {
        loading.postValue(true)
    }

    private fun showMessage() {
        showMessage.postValue(true)
    }

    private fun removeItemError() {
        removeItemError.postValue(true)
    }

}