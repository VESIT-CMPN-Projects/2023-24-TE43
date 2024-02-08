package com.example.empoweher.screen.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.empoweher.R
import com.example.empoweher.composables.HeartAnimation
import com.example.empoweher.composables.onBoarding
import com.example.empoweher.composables.slider
import com.example.empoweher.model.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun Home() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(scrollState)

    ){

        slider()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 10.dp, end = 10.dp, bottom = 70.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.lightblue))
        ){

        }
    }

//    val displayMetrics = context.resources.displayMetrics
//    val dpHeight = displayMetrics.heightPixels / displayMetrics.density
//    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
//    Log.d("dp",dpHeight.toString())
//    Log.d("dp",dpWidth.toString())
}