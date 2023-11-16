package com.example.empoweher.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.empoweher.R
import com.example.empoweher.composables.EventCard
import com.example.empoweher.composables.SampleText
import com.example.empoweher.model.BottomNavigationItem
import com.example.empoweher.model.Screen
import com.example.empoweher.slider


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun Home(
    navigateToNextScreen: (route: String)->Unit,
    ){
//    Column(modifier=Modifier
//        .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        Spacer(modifier=Modifier.height(60.dp))
//        Box(modifier = Modifier.width(350.dp).height(250.dp)) {
//            slider()
//        }
//    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        EventCard(navigateToNextScreen = navigateToNextScreen)
        EventCard(navigateToNextScreen = navigateToNextScreen)
        EventCard(navigateToNextScreen = navigateToNextScreen)
    }

    }