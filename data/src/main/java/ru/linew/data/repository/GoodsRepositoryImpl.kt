package ru.linew.data.repository

import ru.linew.data.GoodsRepository
import ru.linew.data.model.GoodsDto
import ru.linew.data.model.Product
import ru.linew.data.service.DummyJsonService

class GoodsRepositoryImpl(private val service: DummyJsonService) : GoodsRepository {

    override suspend fun getGoods(skip: Int, limit: Int): GoodsDto {
        return service.getGoods(skip, limit)

    }

    override suspend fun getGoodsItem(id: String): Product {
        return service.getGoodsItem(id)
    }
}