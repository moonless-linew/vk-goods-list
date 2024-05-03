package ru.linew.goods.impl.data.repository

import ru.linew.goods.impl.data.model.GoodsDto
import ru.linew.goods.impl.data.service.DummyJsonService

class GoodsRepository(private val service: DummyJsonService) {

    suspend fun getGoods(skip: Int, limit: Int): GoodsDto {
        return service.getGoods(skip, limit)
    }
}