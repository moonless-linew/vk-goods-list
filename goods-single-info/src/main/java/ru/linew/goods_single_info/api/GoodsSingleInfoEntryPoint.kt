package ru.linew.goods_single_info.api

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.linew.data.GoodsRepository
import ru.linew.goods_single_info.impl.GoodsSingleInfoViewModel
import ru.linew.goods_single_info.impl.ui.GoodsSingleInfoScreen
import ru.linew.shared.utils.viewModelFactory

@Composable
fun GoodsSingleInfoEntryPoint(dependencies: GoodsSingleInfoScreenDependencies) {
    val viewModel = viewModel<GoodsSingleInfoViewModel>(factory = viewModelFactory {
        GoodsSingleInfoViewModel(dependencies.goodsRepository)
    })
    GoodsSingleInfoScreen()
}

data class GoodsSingleInfoScreenDependencies(
    val goodsRepository: GoodsRepository
)