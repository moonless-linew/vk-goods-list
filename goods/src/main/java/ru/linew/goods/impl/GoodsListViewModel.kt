package ru.linew.goods.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.map
import ru.linew.goods.api.PAGE_SIZE
import ru.linew.goods.impl.data.repository.GoodsPagingSource
import ru.linew.goods.impl.ui.model.GoodsItemModel
import ru.linew.goods.impl.ui.model.GoodsListUiActions


internal class GoodsListViewModel(source: GoodsPagingSource) : ViewModel() {

    val goodsPager = Pager(PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 5)) { source }.flow.map {
        it.map { item ->
            GoodsItemModel(
                id = item.id,
                title = item.title,
                description = item.description,
                price = "${item.price}$",
                images = item.images
            )
        }
    }.cachedIn(viewModelScope)


    fun onAction(action: GoodsListUiActions) {

    }

}