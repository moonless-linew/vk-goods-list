package ru.linew.vkscrollapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.linew.goods.api.GoodsListScreenPath
import ru.linew.goods_single_info.api.GoodsSingleInfoScreenPath

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = GoodsListScreenPath) {
        goodsListScreen{ navController.navigate(GoodsSingleInfoScreenPath.replace("{id}", it.toString())) }
        goodsSingleInfoScreen { navController.navigateUp() }
    }
}