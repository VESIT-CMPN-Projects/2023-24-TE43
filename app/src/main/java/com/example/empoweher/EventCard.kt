package com.example.empoweher

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*Isko box mai dalna 300dp height aur 250dp width*/
@Composable
fun EventCard(
    painter: Painter,
    contentDescription: String,
    eventTitle: String,
    eventLocation:String,
    eventHost:String,
    eventCapacity: Int
){
    Card(
        modifier= Modifier
            .fillMaxSize(),
        shape= RoundedCornerShape(15.dp),
        elevation=CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ){
        Column(
            modifier=Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painter,
                contentDescription=contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(0.6f)
            )
            Box(modifier= Modifier
                .weight(0.4f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_text),
                    contentDescription = "hello",
                    contentScale = ContentScale.Crop,
                    modifier=Modifier
                        .fillMaxSize()

                )
                Column(
                ) {
                    Text(
                        text = "Title: " + eventTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(2.dp)
                    )
                    Text(
                        text = "Host: " + eventHost,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(2.dp)

                    )
                    Text(
                        text = "Location: " + eventLocation,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(2.dp)

                    )
                    Text(
                        text = "Cost: " + eventCapacity.toString()+" Rs", fontWeight = FontWeight.Bold, fontSize = 16.sp,
                        modifier = Modifier
                            .padding(2.dp)

                    )
                }
            }
        }
    }

}