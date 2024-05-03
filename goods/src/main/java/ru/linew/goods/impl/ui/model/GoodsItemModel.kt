package ru.linew.goods.impl.ui.model

data class GoodsItemModel(
    val title: String,
    val description: String,
    val price: String,
    val images: List<String>
)