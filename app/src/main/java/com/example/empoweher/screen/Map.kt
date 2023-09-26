package com.example.empoweher.screen

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity

@Composable
fun map(){
    val mContext = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {

                Intent(Intent.ACTION_MAIN).also {
                    it.`package`="com.google.android.apps.maps"
                    try {
                        mContext.startActivity(it)
                    } catch (e: ActivityNotFoundException){
                        e.printStackTrace()
                    }
                }
                }
                ) {
                Text(text = "Click on me")
            }
    }
}

