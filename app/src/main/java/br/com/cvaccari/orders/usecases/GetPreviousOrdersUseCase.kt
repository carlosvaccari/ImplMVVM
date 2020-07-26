package br.com.cvaccari.orders.usecases

import br.com.cvaccari.orders.base.BaseUseCase
import br.com.cvaccari.orders.features.models.ItemModel
import br.com.cvaccari.orders.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetPreviousOrdersUseCase(val repository: Repository):
    BaseUseCase<Flow<List<ItemModel>>, Unit>() {

    override suspend fun run(params: Unit): Flow<List<ItemModel>> {
        return repository.getPreviousOrders()
    }
}