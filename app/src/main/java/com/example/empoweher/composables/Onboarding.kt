package com.example.empoweher.composables

import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.empoweher.model.Screen

@Composable
fun onBoarding(navigateToNextScreen: (route: String)->Unit){
    slider()
    
    Button(onClick = {navigateToNextScreen(Screen.Details.route)}){
        Text(text = "skip")
    }
}