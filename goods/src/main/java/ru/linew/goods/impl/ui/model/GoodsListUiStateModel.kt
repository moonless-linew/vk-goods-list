package ru.linew.goods.impl.ui.model

import androidx.paging.PagingData

data class GoodsListUiStateModel(
    val goodsItems: PagingData<GoodsItemModel>
)