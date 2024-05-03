package ru.linew.goods.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.linew.goods.impl.ui.GoodsListScreen

const val GoodsListScreenPath = "goods"

@Composable
fun ConnectEntryPoint() {
    GoodsListScreen()
}