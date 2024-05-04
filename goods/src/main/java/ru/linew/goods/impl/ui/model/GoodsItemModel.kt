package ru.linew.goods.impl.ui.model

internal data class GoodsItemModel(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val images: List<String>
)