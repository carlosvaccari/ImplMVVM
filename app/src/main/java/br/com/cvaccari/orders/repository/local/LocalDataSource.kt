package br.com.cvaccari.orders.repository.local

import br.com.cvaccari.orders.features.models.ItemModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getPreviousOrders(): Flow<List<ItemModel>>

    suspend fun storePreviousOrders(items: List<ItemModel>)

    suspend fun updatePreviousOrders(itemModel: ItemModel) : List<Long>

}