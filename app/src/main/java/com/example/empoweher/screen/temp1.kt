package com.example.empoweher.screen

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.empoweher.EventCard
import com.example.empoweher.R

@Composable

fun temp1(){
        val painter = painterResource(id = R.drawable.event)
        val contentDescription = "Hello"
        val eventTitle = "Women Awareness Drive"
        val eventLocation = "Kalyan"
        val eventHost = "Vaibhav"
        val eventCost = "899"
        val scrollState= rememberScrollState()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier= Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        )
        {
            for (i in 1..50){
                Box(
                    modifier = Modifier
                        .height(250.dp)
                        .width(300.dp),

                    ) {
                    EventCard(
                        painter = painter,
                        contentDescription = contentDescription,
                        eventTitle = eventTitle,
                        eventLocation = eventLocation,
                        eventHost = eventHost,
                        eventCost = eventCost
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
}