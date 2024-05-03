package ru.linew.vkscrollapp.ui

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.linew.goods.api.GoodsListScreenPath
import ru.linew.goods.impl.ui.GoodsListScreen

fun NavGraphBuilder.goodsListScreen() {
    composable(GoodsListScreenPath) {
        GoodsListScreen()
    }
}