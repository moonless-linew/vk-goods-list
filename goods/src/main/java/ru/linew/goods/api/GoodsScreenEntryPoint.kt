package ru.linew.goods.api

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.Dispatchers
import ru.linew.data.GoodsRepository
import ru.linew.goods.impl.GoodsListViewModel
import ru.linew.goods.impl.GoodsPagingSource
import ru.linew.goods.impl.ui.GoodsListScreen
import ru.linew.shared.utils.viewModelFactory

const val GoodsListScreenPath = "goods"

@Composable
fun GoodsListEntryPoint(dependencies: GoodsScreenDependencies) {
    val source = GoodsPagingSource(dependencies.goodsRepository)
    val viewModel = viewModel<GoodsListViewModel>(factory = viewModelFactory {
        GoodsListViewModel(source)
    })
    GoodsListScreen(
        goodsItems = viewModel.goodsPager.collectAsLazyPagingItems(Dispatchers.IO),
        uiActions = viewModel::onAction
    )
}

data class GoodsScreenDependencies(
    val goodsRepository: GoodsRepository
)