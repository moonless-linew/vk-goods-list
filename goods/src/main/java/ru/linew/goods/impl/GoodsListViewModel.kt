package ru.linew.goods.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.Dispatcher
import ru.linew.goods.api.PAGE_SIZE
import ru.linew.goods.impl.data.repository.GoodsPagingSource
import ru.linew.goods.impl.ui.model.GoodsItemModel
import ru.linew.goods.impl.ui.model.GoodsListUiActions


class GoodsListViewModel(source: GoodsPagingSource) : ViewModel() {

    val goodsPager = Pager(PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = 1)) { source }.flow.map {
        it.map { item ->
            GoodsItemModel(
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