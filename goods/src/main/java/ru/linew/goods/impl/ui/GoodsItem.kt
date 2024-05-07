package ru.linew.goods.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.linew.goods.R
import ru.linew.shared.ui.VkScrollAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun GoodsItem(
    modifier: Modifier = Modifier,
    id: Int,
    title: String,
    description: String,
    price: String,
    originalPrice: String?,
    images: List<String>,
    onClick: (Int) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    val shimmer = remember { mutableStateOf(true) }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(id) }
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .height(320.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(0.55f)
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
                    model = images[page],
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    onSuccess = { shimmer.value = false }
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp)
            ) {
                repeat(images.size) {
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
        Column(
            modifier = Modifier
                .padding(5.dp)
                .weight(0.25f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    softWrap = true,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    modifier = Modifier.basicMarquee(
                        iterations = 2,
                        spacing = MarqueeSpacing(8.dp),
                        velocity = 12.dp,
                        animationMode = MarqueeAnimationMode.Immediately
                    ),
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = price,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.padding(4.dp))
                originalPrice?.let {
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
        }
    }

}


@Preview(backgroundColor = 0xFFB6B6B6, showBackground = true)
@Composable
private fun GoodsItemPreview() {
    VkScrollAppTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            GoodsItem(
                modifier = Modifier
                    .weight(1f),
                id = 0,
                title = "VK",
                description = "VK",
                price = "1 000 $",
                originalPrice = "1 200$",
                images = listOf(R.drawable.test_image.toString()),
                onClick = { }
            )
            Spacer(modifier = Modifier.padding(8.dp))
            GoodsItem(
                modifier = Modifier
                    .weight(1f),
                id = 0,
                title = "VKVKVKVKVKVKVKVKVKVKVKVKVKVKVKVKVKVKVKVK",
                description = "VK",
                price = "Бесценно $",
                originalPrice = null,
                images = listOf(
                    R.drawable.test_image.toString(),
                    R.drawable.test_image.toString(),
                    R.drawable.test_image.toString(),
                    R.drawable.test_image.toString(),
                    R.drawable.test_image.toString(),
                    R.drawable.test_image.toString(),
                ),
                onClick = {}
            )
        }
    }
}

@Preview(
    backgroundColor = 0xFF000000, showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun GoodsItemPreviewNight() {
    VkScrollAppTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(250.dp)
        ) {
            GoodsItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                id = 0,
                title = "VK",
                description = "VK",
                price = "1 000 $",
                originalPrice = "1 200$",
                images = listOf(R.drawable.test_image.toString()),
                onClick = {}
            )
            Spacer(modifier = Modifier.padding(8.dp))
            GoodsItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                id = 0,
                title = "VK",
                description = "VK",
                price = "1 000 $",
                originalPrice = null,
                images = listOf(R.drawable.test_image.toString()),
                onClick = {}
            )
        }
    }
}
