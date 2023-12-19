package com.example.empoweher.screen.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.empoweher.composables.getInfoUser

@Composable
fun Profile(userId : String?=null,navigateToNextScreen: (route: String)->Unit) {
    val name= getInfoUser(thing = "name", userId =userId )
    Text(text = name)
}