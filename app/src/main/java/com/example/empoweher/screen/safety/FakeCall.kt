package com.example.empoweher.screen.safety

import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import kotlinx.coroutines.delay

@Preview(showBackground = true, heightDp = 790, widthDp = 400, showSystemUi = true)
@Composable
fun FakeCall() {
    val mContext = LocalContext.current
    val mediaPlayer = MediaPlayer.create(mContext,R.raw.music)
    LaunchedEffect(key1 = Unit){
        Toast.makeText(mContext,"Fake Call in 3 sec",Toast.LENGTH_SHORT).show()
        delay(3000)
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
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.stop()
        }
    }
}