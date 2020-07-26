package br.com.cvaccari.orders.repository.local.database

import androidx.room.TypeConverter
import br.com.cvaccari.orders.features.models.ItemModel
import com.google.gson.Gson

class TypeConverters {

    private val gson = Gson()

    @TypeConverter
    fun toString(itemModel: ItemModel) = gson.toJson(itemModel, ItemModel::class.java)

    @TypeConverter
    fun toItemModel(string: String) = gson.fromJson(string, ItemModel::class.java)

}