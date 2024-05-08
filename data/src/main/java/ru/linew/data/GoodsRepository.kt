package ru.linew.data

import ru.linew.data.model.GoodsDto
import ru.linew.data.model.Product

interface GoodsRepository {

    suspend fun getGoods(skip: Int, limit: Int): GoodsDto

    suspend fun getGoodsItem(id: Int): Product

}