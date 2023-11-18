package com.example.empoweher.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.empoweher.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun Home(
    navigateToNextScreen: (route: String)->Unit,
    ){
    Column(modifier=Modifier
        .fillMaxSize()
        .background(colorResource(R.color.cream)),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

    }

    }