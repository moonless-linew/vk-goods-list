package ru.linew.goods.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import ru.linew.goods.impl.ui.model.GoodsItemModel
import ru.linew.goods.impl.ui.model.GoodsListUiActions

@Composable
fun GoodsListScreen(
    modifier: Modifier = Modifier,
    goodsItems: LazyPagingItems<GoodsItemModel>,
    uiActions: (GoodsListUiActions) -> Unit
) {
    Box {
        when (goodsItems.loadState.refresh) {
            LoadState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is LoadState.Error -> {
                Text(modifier = Modifier.align(Alignment.Center), text = "Error")
            }

            else -> {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(goodsItems.itemCount / 2) { index ->
                        Row {
                            goodsItems[index * 2]?.let {
                                GoodsItem(
                                    modifier = Modifier
                                        .weight(1f),
                                    title = it.title,
                                    description = it.description,
                                    price = it.price,
                                    images = it.images
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            goodsItems[index * 2 + 1]?.let {
                                GoodsItem(
                                    modifier = Modifier
                                        .weight(1f),
                                    title = it.title,
                                    description = it.description,
                                    price = it.price,
                                    images = it.images
                                )
                            } ?: Box(modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                    item {
                        if (goodsItems.loadState.append is LoadState.Loading) {
                            Box(modifier = Modifier.fillMaxWidth()){
                                CircularProgressIndicator(modifier.align(Alignment.Center))
                            }
                        }
                    }
                }
            }
        }

    }
}