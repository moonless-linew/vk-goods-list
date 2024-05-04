package ru.linew.goods.impl.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.linew.goods.api.PAGE_SIZE
import ru.linew.goods.impl.data.model.Product

internal class GoodsPagingSource(private val repository: GoodsRepository) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val nextPageNumber = params.key ?: 0
            withContext(Dispatchers.IO) {
                val response = repository.getGoods(nextPageNumber, PAGE_SIZE)
                LoadResult.Page(
                    data = response.products,
                    prevKey = null,
                    nextKey = (response.skip + PAGE_SIZE).takeIf { response.total > it }
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int {
        return ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2).coerceAtLeast(0)
    }

}