package ru.linew.vkscrollapp.ui

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.linew.goods.api.GoodsListEntryPoint
import ru.linew.goods.api.GoodsListScreenPath
import ru.linew.goods.api.GoodsScreenDependencies
import ru.linew.vkscrollapp.VkScrollApp

fun NavGraphBuilder.goodsListScreen() {
    composable(GoodsListScreenPath) {
        val application = LocalContext.current.applicationContext as VkScrollApp
        GoodsListEntryPoint(GoodsScreenDependencies(application.goodsRepository))
    }
}