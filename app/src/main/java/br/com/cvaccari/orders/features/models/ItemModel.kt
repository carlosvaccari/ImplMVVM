package br.com.cvaccari.orders.features.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.cvaccari.orders.BuildConfig
import br.com.cvaccari.orders.R
import br.com.cvaccari.orders.commons.extensions.toCurrency
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class ItemModel(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("item_count") var itemCount: Int,
    @SerializedName("description") val description: String,
    @SerializedName("description_quantity") val quantityDescription: String,
    @SerializedName("current_price") val currentPrice: Double,
    @SerializedName("previous_price") val previousPrice: Double?,
    @SerializedName("image_url") val imageUrl: String?
) : Serializable {

    val currentPriceFormatted
        get() = currentPrice.toCurrency()

    val previousPriceFormatted
        get() = previousPrice?.toCurrency()

    val imageUrlFormatted
        get() = BuildConfig.BASE_IMAGE_URL.plus(imageUrl)

    val itemCountFormatted
        get() = itemCount.toString()

}