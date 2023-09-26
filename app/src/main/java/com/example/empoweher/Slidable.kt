package com.example.empoweher

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, heightDp = 790, widthDp = 400, showSystemUi = true)
@Composable
fun slider() {
    val values = listOf(R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4)
    val pagerState = rememberPagerState {
        values.size
    }
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxWidth())
    {
        HorizontalPager(state = pagerState)
        { index ->
            IconButton(onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        pagerState.currentPage - 1
                    )
                }
            }, modifier = Modifier.align(Alignment.CenterStart))
            {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "left"
                )
            }
            Image(
                painter = painterResource(id = values[index]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(250.dp)
            )
            IconButton(onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        pagerState.currentPage + 1
                    )
                }
            }, modifier = Modifier.align(Alignment.CenterStart))
            {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_24),
                    contentDescription = "left"
                )
            }
        }
    }
}