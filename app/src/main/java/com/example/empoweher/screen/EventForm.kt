package com.example.empoweher.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.empoweher.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventForm(){
    var name by remember {
        mutableStateOf("")
    }
    Box (modifier = Modifier
        .fillMaxSize()

    )
    {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Create Event",
                    fontSize = 35.sp,
                    fontFamily = FontFamily(Font(R.font.font1)),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.purple_200)

                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = "Event Name:",
                    fontSize = 23.sp,
                    fontFamily = FontFamily(Font(R.font.font1)),

                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { str ->
                        name = str

                    })



            }


        }
    }

}