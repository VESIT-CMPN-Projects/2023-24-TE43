package com.example.empoweher

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Preview(showBackground = true, widthDp = 390, heightDp = 770, showSystemUi = true)
@Composable
fun Login_otp(){
    val textFieldState= remember {
        mutableStateOf("")
    }
    Column(
        modifier= Modifier
            .fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.otp),contentDescription = "Login",modifier=Modifier
                .size(390.dp),contentScale = ContentScale.Fit
            )}
        Box(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 30.dp))
        {
            TextField(value =  textFieldState.value,label = {
                Text("Enter Your OTP")}, onValueChange = {
                textFieldState.value=it
            }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),modifier=Modifier.align(Alignment.CenterEnd).width(335.dp))
            Image(painter = painterResource(id = R.drawable.next), contentDescription = "Get otp", contentScale = ContentScale.Fit, modifier = Modifier.size(50.dp).align(
                Alignment.CenterEnd))
        }

    }
}