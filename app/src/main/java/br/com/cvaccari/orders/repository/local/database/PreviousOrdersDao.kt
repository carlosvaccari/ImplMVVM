package br.com.cvaccari.orders.repository.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.cvaccari.orders.features.models.ItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PreviousOrdersDao {

    @Query("SELECT * FROM ItemModel")
    fun getAllPreviousOrders(): Flow<List<ItemModel>>

    @Insert(onConflict = REPLACE)
    suspend fun storePreviousOrders(items: List<ItemModel>): List<Long>

}