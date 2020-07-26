package br.com.cvaccari.orders.previousorders

import br.com.cvaccari.orders.commons.adapters.ItemsAdapter
import br.com.cvaccari.orders.features.models.ItemModel

object PreviousOrdersHelper {

    fun getItemsList(): List<ItemModel> {
        return listOf(
            ItemModel(
                1, "Teste1", 10, "12312", "12312", 13.toDouble(), 123.toDouble(), "asda"
            ),
            ItemModel(
                1, "Teste2", 10, "12312", "12312", 13.toDouble(), 123.toDouble(), "asda"
            ),
            ItemModel(
                1, "Teste3", 10, "12312", "12312", 13.toDouble(), 123.toDouble(), "asda"
            )
        )
    }

    fun newItemCount() =  ItemModel(
        1, "asda", 100, "12312", "12312", 13.toDouble(), 123.toDouble(), "asda"
    )
}