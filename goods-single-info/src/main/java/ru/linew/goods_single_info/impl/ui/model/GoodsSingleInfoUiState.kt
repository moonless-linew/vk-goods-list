package ru.linew.goods_single_info.impl.ui.model

import androidx.compose.runtime.Stable

@Stable
data class GoodsSingleInfoUiState(
    val id: Int,
    val brand: String,
    val category: String,
    val description: String,
    val images: List<String>,
    val price: String,
    val rating: String,
    val originalPrice: String?,
    val stock: Int,
    val thumbnail: String,
    val title: String
)