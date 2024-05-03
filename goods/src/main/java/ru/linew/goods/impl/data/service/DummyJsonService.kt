package ru.linew.goods.impl.data.service

import retrofit2.http.GET
import retrofit2.http.Query
import ru.linew.goods.impl.data.model.GoodsDto

interface DummyJsonService {

    @GET("products")
    suspend fun getGoods(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
    ): GoodsDto
}