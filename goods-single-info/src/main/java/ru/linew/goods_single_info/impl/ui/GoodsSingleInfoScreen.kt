package ru.linew.goods_single_info.impl.ui

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.linew.goods_single_info.R
import ru.linew.goods_single_info.impl.ui.model.GoodsSingleInfoScreenStatus
import ru.linew.goods_single_info.impl.ui.model.GoodsSingleInfoUiState
import ru.linew.shared.ui.VkScrollAppTheme
import ru.linew.shared.utils.LockScreenOrientation
import ru.linew.shared.utils.shimmerBrush

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun GoodsSingleInfoScreen(
    modifier: Modifier = Modifier,
    uiState: GoodsSingleInfoScreenStatus,
    onBack: () -> Unit
) {
    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                    contentDescription = null
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = (uiState as? GoodsSingleInfoScreenStatus.Success)?.state?.title
                            ?: "",
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                GoodsSingleInfoScreenStatus.Failure -> {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(R.string.error)
                    )
                }

                GoodsSingleInfoScreenStatus.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is GoodsSingleInfoScreenStatus.Success -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        val pagerState =
                            rememberPagerState(pageCount = { uiState.state.images.size })
                        val shimmer = remember { mutableStateOf(true) }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(12.dp))
                        ) {
                            HorizontalPager(
                                state = pagerState
                            ) { page ->
                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            shimmerBrush(
                                                targetValue = 1300f,
                                                showShimmer = shimmer.value
                                            )
                                        ),
                                    model = uiState.state.images[page],
                                    contentDescription = uiState.state.title,
                                    contentScale = ContentScale.Crop,
                                    onSuccess = { shimmer.value = false }
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 4.dp)
                            ) {
                                repeat(uiState.state.images.size) {
                                    val color =
                                        if (pagerState.currentPage == it) MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.surfaceContainerHigh
                                    Spacer(modifier = Modifier.padding(1.dp))
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(6.dp)
                                            .background(color)
                                    )
                                    Spacer(modifier = Modifier.padding(1.dp))
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Text(
                                text = uiState.state.title,
                                style = MaterialTheme.typography.headlineMedium,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.padding(2.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = uiState.state.price,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                uiState.state.originalPrice?.let {
                                    Text(
                                        text = it,
                                        style = MaterialTheme.typography.titleMedium
                                            .copy(
                                                textDecoration = TextDecoration.LineThrough,
                                                fontStyle = FontStyle.Italic
                                            ),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                            HorizontalDivider(modifier = Modifier.padding(8.dp))
                            Param(title = stringResource(R.string.brand), content = uiState.state.brand)
                            Param(title = stringResource(R.string.category), content = uiState.state.category)
                            Param(title = stringResource(R.string.stock), content = uiState.state.stock.toString())
                            Param(title = stringResource(R.string.rating), content = uiState.state.rating)
                            HorizontalDivider(modifier = Modifier.padding(8.dp))

                            Text(
                                modifier = Modifier.padding(vertical = 4.dp),
                                text = stringResource(R.string.description),
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Text(
                                text = uiState.state.description,
                                style = MaterialTheme.typography.bodyLarge
                            )

                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Param(title: String, content: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = content,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview
@Composable
private fun GoodsSingleInfoScreenPreview() {
    VkScrollAppTheme {
        GoodsSingleInfoScreen(
            uiState = GoodsSingleInfoScreenStatus.Success(
                state = GoodsSingleInfoUiState(
                    brand = "Title",
                    category = "Cars",
                    description = "Title",
                    id = 5,
                    images = listOf(),
                    price = "2000$",
                    originalPrice = "3000$",
                    rating = "4.5",
                    stock = 100,
                    thumbnail = "",
                    title = "Title"
                )
            )
        ) { /*nothing*/ }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun GoodsSingleInfoScreenPreviewNight() {
    VkScrollAppTheme {
        GoodsSingleInfoScreen(
            uiState = GoodsSingleInfoScreenStatus.Success(
                state = GoodsSingleInfoUiState(
                    brand = "Title",
                    category = "Cars",
                    description = "Title",
                    id = 5,
                    images = listOf(),
                    price = "2000$",
                    originalPrice = "3000&",
                    rating = "4.5",
                    stock = 100,
                    thumbnail = "",
                    title = "Title"
                )
            )
        ) { /*nothing*/ }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun GoodsSingleInfoScreenPreviewError() {
    VkScrollAppTheme {
        GoodsSingleInfoScreen(
            uiState = GoodsSingleInfoScreenStatus.Failure
        ) { /*nothing*/ }
    }
}