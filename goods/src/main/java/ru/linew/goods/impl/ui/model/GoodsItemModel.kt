package ru.linew.goods.impl.ui.model

import androidx.compose.runtime.Stable

@Stable
internal data class GoodsItemModel(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val originalPrice: String?,
    val images: List<String>
)