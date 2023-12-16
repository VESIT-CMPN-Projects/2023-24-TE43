package com.example.empoweher.screen.Details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Details(navigateToNextScreen: (route: String)->Unit){
    var name by remember{
        mutableStateOf("")
    }

    Column {
        Text(text = "Enter Details To Continue")
        OutlinedTextField(
            value = name,
            label = { Text(text = "Enter Name") },
            onValueChange = { str ->
                name = str
            },modifier= Modifier.padding(end = 10.dp))


    }
}

