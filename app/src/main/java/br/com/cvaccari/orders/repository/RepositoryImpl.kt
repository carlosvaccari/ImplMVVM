package br.com.cvaccari.orders.repository

import br.com.cvaccari.orders.commons.connection.ConnectionManager
import br.com.cvaccari.orders.features.models.ItemModel
import br.com.cvaccari.orders.repository.local.LocalDataSource
import br.com.cvaccari.orders.repository.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val connectionManager: ConnectionManager
) : Repository {

    override suspend fun getPreviousOrders(): Flow<List<ItemModel>> {
        if (connectionManager.isConnected()) {
            val result = remoteDataSource.getPreviousOrders()
            localDataSource.storePreviousOrders(
                result
            )
        }

        return localDataSource.getPreviousOrders()
    }

    override suspend fun updatePreviousOrders(itemModel: ItemModel): List<Long> {
        return localDataSource.updatePreviousOrders(itemModel)
    }
}
