package br.com.cvaccari.orders.repository.remote

import br.com.cvaccari.orders.features.models.ItemModel
import retrofit2.http.GET

interface RemoteDataSource {

    @GET("previous-orders")
    suspend fun getPreviousOrders(): List<ItemModel>
}