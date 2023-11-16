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
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.empoweher.R

@Composable
fun EventCard(
    eventTitle: String,
    eventAddress:String,
    eventCity:String,
    eventCapacity:String,
    eventStartDate:String,
    eventEndDate:String,
    eventTiming:String,
    eventCost:String,
    eventImage:String
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
            val painter= rememberAsyncImagePainter(model = eventImage)
            Image(
                painter = painter,
                contentDescription="cd",
                contentScale = ContentScale.Fit,
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
                        text = "Title: $eventTitle",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(2.dp)
                    )
                    Text(
                        text = "Address : $eventAddress",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(2.dp)

                    )
                    Text(
                        text = "City : " + eventCity,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(2.dp)

                    )
                    Text(
                        text = "Event Calendar : " + eventStartDate+" to "+eventEndDate, fontWeight = FontWeight.Bold, fontSize = 16.sp,
                        modifier = Modifier
                            .padding(2.dp)

                    )
                    Text(
                        text = "Event Timings : " + eventTiming, fontWeight = FontWeight.Bold, fontSize = 16.sp,
                        modifier = Modifier
                            .padding(2.dp)

                    )
                    Text(
                        text = "Event Capacity : " + eventCapacity, fontWeight = FontWeight.Bold, fontSize = 16.sp,
                        modifier = Modifier
                            .padding(2.dp)

                    )
                    Text(
                        text = "Cost : " + eventCost,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(2.dp)

                    )
                }
            }
        }
    }

}