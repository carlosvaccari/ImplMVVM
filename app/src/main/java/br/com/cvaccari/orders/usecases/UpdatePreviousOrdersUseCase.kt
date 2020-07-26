package br.com.cvaccari.orders.usecases

import br.com.cvaccari.orders.base.BaseUseCase
import br.com.cvaccari.orders.features.models.ItemModel
import br.com.cvaccari.orders.repository.Repository
import kotlinx.coroutines.flow.Flow

class UpdatePreviousOrdersUseCase(val repository: Repository):
    BaseUseCase<Unit, ItemModel>() {

    override suspend fun run(params: ItemModel) {
        repository.updatePreviousOrders(params)
    }
}