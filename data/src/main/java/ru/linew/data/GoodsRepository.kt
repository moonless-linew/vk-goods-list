package ru.linew.data

import ru.linew.data.model.GoodsDto

interface GoodsRepository {

    suspend fun getGoods(skip: Int, limit: Int): GoodsDto

}