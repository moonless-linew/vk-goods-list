package ru.linew.goods_single_info.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.linew.data.GoodsRepository
import ru.linew.goods_single_info.impl.GoodsSingleInfoViewModel
import ru.linew.goods_single_info.impl.ui.GoodsSingleInfoScreen
import ru.linew.shared.utils.viewModelFactory

const val GoodsSingleInfoScreenPath = "single/{id}"

@Composable
fun GoodsSingleInfoEntryPoint(
    dependencies: GoodsSingleInfoScreenDependencies,
    id: Int,
    onBack: () -> Unit
) {
    val viewModel = viewModel<GoodsSingleInfoViewModel>(factory = viewModelFactory {
        GoodsSingleInfoViewModel(dependencies.goodsRepository, id)
    })
    GoodsSingleInfoScreen(uiState = viewModel.uiState.collectAsState().value, onBack = onBack)
}

data class GoodsSingleInfoScreenDependencies(
    val goodsRepository: GoodsRepository
)