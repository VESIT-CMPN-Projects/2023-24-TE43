package com.example.empoweher.screen.temp

import android.content.Intent
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.empoweher.activities.LocationActivity

@Composable
fun Temp1(){
    val context= LocalContext.current
   LaunchedEffect(Unit){
       Toast.makeText(context,"WILL BE IMPLEMENTED SOON...",Toast.LENGTH_SHORT).show()
   }
}