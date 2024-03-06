package com.example.empoweher.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.with
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R
import com.example.empoweher.auth.signin.TypewriterText
import com.example.empoweher.screen.Details.converterHeight
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true, heightDp = 790, widthDp = 400, showSystemUi = true)
@Composable
fun slider() {
    val images = listOf(R.drawable.indiragandhi, R.drawable.indranooyi,R.drawable.kalpanachawla,R.drawable.kiranbedi,R.drawable.marykom)
    val links= listOf("https://www.history.com/topics/asian-history/indira-gandhi","https://hbr.org/2015/09/how-indra-nooyi-turned-design-thinking-into-strategy","https://www.linkedin.com/pulse/inspiring-story-women-achiever-kalpana-chawla-karemullasha-m-p","https://www.britannica.com/biography/Kiran-Bedi","https://www.potsandpans.in/blogs/articles/mary-kom-the-epitome-of-women-empowerment#:~:text=Mary%20had%20a%20tough%20childhood,in%20Asian%20Games%20in%201998.")
    val achievments= listOf("First Women Prime Minister of India - Indira Gandhi","CEO of PepsiCo - Indra Nooyi","Indian American Astronaut and Aerospace Engineer - Kalpana Chawla","Former Lieutenant Governor of Puducherry - Kiran Bedi","Indian Boxer and 2012 Olympic Medalist - Mary Kom")
    val pagerState = rememberPagerState(
        pageCount ={ images.size}
    )
    LaunchedEffect(Unit) {
        while (true) {
            delay(6000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
    }
    val scope = rememberCoroutineScope()
    val context= LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            HorizontalPager(
                state = pagerState,
                Modifier
                    .wrapContentSize()

            ) { currentPage ->

                Card(
                    Modifier
                        .wrapContentSize()
                        .fillMaxHeight(0.9f),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = images[currentPage]),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                                .clickable{
                                    val urlIntent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(links[currentPage])
                                    )
                                    context.startActivity(urlIntent)
                                }
                        )
                        Box(modifier= Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Black),
                                    startY = 300f
                                )
                            ))
                        Box(modifier = Modifier.fillMaxSize()
                            .padding(top = converterHeight(12, LocalContext.current).dp),
                            contentAlignment = Alignment.BottomStart
                        )
                        {
                            SampleText(text = achievments[currentPage], fontSize = 20, textColor = Color.White)
                        }
                    }
                }
            }
        }

        PageIndicator(
            pageCount = images.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier
        )
    }


}



@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top=20.dp)
    ) {
        repeat(pageCount){
            IndicatorDots(isSelected = it == currentPage, modifier= modifier)
        }
    }
}

@Composable
fun IndicatorDots(isSelected: Boolean, modifier: Modifier) {
    val size = animateDpAsState(targetValue = if (isSelected) 12.dp else 10.dp, label = "")
    Box(modifier = modifier
        .padding(2.dp)
        .size(size.value)
        .clip(CircleShape)
        .background(if (isSelected) Color(0xff373737) else Color(0xA8373737))
    )
}