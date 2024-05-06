package ru.linew.data.model


import com.google.gson.annotations.SerializedName

data class GoodsDto(
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("total")
    val total: Int
)