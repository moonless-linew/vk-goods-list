package ru.linew.vkscrollapp.ui

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.linew.goods.api.GoodsListEntryPoint
import ru.linew.goods.api.GoodsListScreenPath
import ru.linew.goods.api.GoodsScreenDependencies
import ru.linew.goods_single_info.api.GoodsSingleInfoEntryPoint
import ru.linew.goods_single_info.api.GoodsSingleInfoScreenDependencies
import ru.linew.goods_single_info.api.GoodsSingleInfoScreenPath
import ru.linew.vkscrollapp.VkScrollApp

fun NavGraphBuilder.goodsListScreen(onItemClick: (id: Int) -> Unit) {
    composable(GoodsListScreenPath) {
        val application = LocalContext.current.applicationContext as VkScrollApp
        GoodsListEntryPoint(
            dependencies = GoodsScreenDependencies(application.goodsRepository),
            onItemClick = onItemClick
        )
    }
}

fun NavGraphBuilder.goodsSingleInfoScreen(onBack: () -> Unit) {
    composable(
        route = GoodsSingleInfoScreenPath,
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) { navBackStackEntry ->
        val application = LocalContext.current.applicationContext as VkScrollApp
        GoodsSingleInfoEntryPoint(
            dependencies = GoodsSingleInfoScreenDependencies(application.goodsRepository),
            id = navBackStackEntry.arguments?.getInt("id") ?: 1,
            onBack = onBack,
        )
    }
}