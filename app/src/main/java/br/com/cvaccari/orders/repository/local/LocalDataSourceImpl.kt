package br.com.cvaccari.orders.repository.local

import android.util.Log
import br.com.cvaccari.orders.features.models.ItemModel
import br.com.cvaccari.orders.repository.local.database.PreviousOrdersDao
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val previousOrdersDao: PreviousOrdersDao
) : LocalDataSource {

    override suspend fun getPreviousOrders(): Flow<List<ItemModel>> {
        return previousOrdersDao.getAllPreviousOrders()
    }

    override suspend fun storePreviousOrders(items: List<ItemModel>) {
        previousOrdersDao.storePreviousOrders(items)
    }

    override suspend fun updatePreviousOrders(itemModel: ItemModel): List<Long> {
       return previousOrdersDao.storePreviousOrders(listOf(itemModel))
    }
}