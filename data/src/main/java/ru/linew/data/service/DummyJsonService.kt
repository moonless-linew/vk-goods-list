package ru.linew.data.service

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.linew.data.model.GoodsDto
import ru.linew.data.model.Product

interface DummyJsonService {

    @GET("products")
    suspend fun getGoods(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
    ): GoodsDto

    @GET("products/{id}")
    suspend fun getGoodsItem(@Path("id") id: Int): Product
}