package com.example.empoweher.screen.temp

import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.empoweher.LocationActivity

@Composable
fun Temp1(){
    val context= LocalContext.current
    Button(onClick = {
        val navigate = Intent(context, LocationActivity::class.java)
        context.startActivity(navigate)
    }) {
        Text(text = "Go to Location")
    }
}