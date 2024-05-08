package ru.linew.goods_single_info.impl.ui.model

sealed class GoodsSingleInfoScreenStatus {
    data object Loading : GoodsSingleInfoScreenStatus()
    data object Failure : GoodsSingleInfoScreenStatus()
    data class Success(val state: GoodsSingleInfoUiState) : GoodsSingleInfoScreenStatus()
}