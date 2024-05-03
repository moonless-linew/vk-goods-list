package ru.linew.goods.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.linew.goods.R
import ru.linew.shared.ui.VkScrollAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GoodsItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    price: String,
    images: List<String>
) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .height(320.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                modifier = Modifier
                    .weight(0.55f)
                    .clip(RoundedCornerShape(12.dp)),
                state = pagerState
            ) { page ->
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = images[page],
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
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
                Text(
                    text = price,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
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
                title = "VK",
                description = "VK",
                price = "1 000 $",
                images = listOf(R.drawable.test_image.toString())
            )
            Spacer(modifier = Modifier.padding(8.dp))
            GoodsItem(
                modifier = Modifier
                    .weight(1f),
                title = "VdsfdfsdfsdfsdfsdfssfddfdsfdsfdsfdsfdsfdfsfdsdfsdfsdsfK",
                description = "VK",
                price = "1 000 $",
                images = listOf(
                    R.drawable.test_image.toString(),
                    R.drawable.test_image.toString(),
                    R.drawable.test_image.toString(),
                    R.drawable.test_image.toString(),
                    R.drawable.test_image.toString(),
                    R.drawable.test_image.toString(),
                )
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
                title = "VK",
                description = "VK",
                price = "1 000 $",
                images = listOf(R.drawable.test_image.toString())
            )
            Spacer(modifier = Modifier.padding(8.dp))
            GoodsItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                title = "VK",
                description = "VK",
                price = "1 000 $",
                images = listOf(R.drawable.test_image.toString())
            )
        }
    }
}
