package com.example.empoweher.screen

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.empoweher.R

@Preview(showBackground = true, heightDp = 790, widthDp = 400, showSystemUi = true)
@Composable
fun FakeCall() {
    val mContext = LocalContext.current
    val mediaPlayer = MediaPlayer.create(mContext,R.raw.music)
    LaunchedEffect(key1 = Unit){
        mediaPlayer.start()
    }
    var isPlaying by remember {mutableStateOf(true)}
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                if (isPlaying) {
                    isPlaying = false
                    mediaPlayer.stop()
                } else {
                    isPlaying = true
                    mediaPlayer.start()
                }
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.fake_call),
            contentScale = ContentScale.FillWidth,
            contentDescription = "fake_call",
            modifier = Modifier.fillMaxSize()
        )
    }
}