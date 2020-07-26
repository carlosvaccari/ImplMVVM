package br.com.cvaccari.orders.repository

import br.com.cvaccari.orders.features.models.ItemModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getPreviousOrders(): Flow<List<ItemModel>>

    suspend fun updatePreviousOrders(itemModel: ItemModel) : List<Long>
}