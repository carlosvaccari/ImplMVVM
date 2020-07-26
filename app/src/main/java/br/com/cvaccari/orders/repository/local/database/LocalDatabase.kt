package br.com.cvaccari.orders.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.cvaccari.orders.features.models.ItemModel

@Database(entities = [ItemModel::class], version = 1)
@TypeConverters(br.com.cvaccari.orders.repository.local.database.TypeConverters::class)
abstract class LocalDatabase: RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "LocalDatabase"
    }

    abstract fun itemModelDao(): PreviousOrdersDao

}