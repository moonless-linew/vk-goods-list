package ru.linew.goods.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import java.util.UUID

@Composable
internal fun GoodsListScreen(
    modifier: Modifier = Modifier,
    goodsItems: LazyPagingItems<GoodsItemModel>,
    onTransitionToItem: (Int) -> Unit
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
                    items(
                        count = goodsItems.itemCount / 2,
                        key = { goodsItems[it]?.id ?: UUID.randomUUID() }) { index ->
                        Row {
                            repeat(2) {
                                goodsItems[index * 2 + it]?.let {
                                    GoodsItem(
                                        modifier = Modifier
                                            .weight(1f),
                                        id = it.id,
                                        title = it.title,
                                        description = it.description,
                                        price = it.price,
                                        originalPrice = it.originalPrice,
                                        images = it.images,
                                        onClick = onTransitionToItem
                                    )
                                } ?: Box(modifier = Modifier.weight(1f))
                                Spacer(modifier = Modifier.padding(6.dp))
                            }
                        }
                        Spacer(modifier = Modifier.padding(6.dp))
                    }
                    item {
                        if (goodsItems.loadState.append is LoadState.Loading) {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                CircularProgressIndicator(modifier.align(Alignment.Center))
                            }
                        }
                    }
                }
            }
        }

    }
}