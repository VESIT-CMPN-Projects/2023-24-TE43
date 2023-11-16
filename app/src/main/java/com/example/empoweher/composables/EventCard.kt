package com.example.empoweher.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R
import com.example.empoweher.model.Screen


@Composable
fun EventCard(navigateToNextScreen: (route: String) -> Unit){

    var color = colorResource(id = R.color.cream)

    if(true){
        color = colorResource(id = R.color.emeraldgreen)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(10.dp)
            .clickable {
                navigateToNextScreen(Screen.DetailedEventCard.route)
            },
        colors = CardDefaults.cardColors(containerColor = color,
    )
    ){
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .fillMaxHeight()
            ) {
                Card(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(painter = painterResource(id = R.drawable.police),
                        contentDescription = "EventImage",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .clip(RoundedCornerShape(10.dp)))
                }
            }

            Column(modifier = Modifier
                .fillMaxWidth(.8f)
                .fillMaxHeight()) {
                Spacer(modifier = Modifier.height(5.dp))
                SampleText("Event : Mane's Farewell")
                SampleText("Category : Speech")
                SampleText("Location : 601")
                SampleText("Fees : Kuch nhi")
            }
        }
    }
}

@Composable
fun SampleText(text: String){
    Text(text = text,
        fontFamily = FontFamily(Font(R.font.font1)),
        fontSize = 10.sp,
        modifier = Modifier
            .padding(10.dp,0.dp))
}