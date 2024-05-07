package ru.linew.goods.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.map
import ru.linew.goods.impl.ui.model.GoodsItemModel
import kotlin.math.roundToInt


internal class GoodsListViewModel(source: GoodsPagingSource) : ViewModel() {

    val goodsPager = Pager(PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 5)) { source }.flow
        .map {
            it.map { item ->
                val hasDiscount = item.discountPercentage != 0.0
                GoodsItemModel(
                    id = item.id,
                    title = item.title,
                    description = item.description,
                    price = "${item.price}$",
                    originalPrice = if (hasDiscount) "${(item.price * (1 + item.discountPercentage / 100.0)).roundToInt()}$" else null,
                    images = item.images
                )
            }
        }.cachedIn(viewModelScope)
}