package com.example.empoweher.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.sourceInformationMarkerStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R

@Preview(widthDp = 432, heightDp = 914, showBackground = true)
@Composable
fun ProfileScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
//        Icon(imageVector = Icons.Rounded.Person, contentDescription = "Account",
//            modifier = Modifier.size(50.dp))
        Text(text = "Aman Maane",
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
        )

        Row(
            modifier = Modifier.padding(start = 30.dp)
        ){
            Image(painter = painterResource(id = R.drawable.alert),
                contentDescription = "ProfilePic",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
            )
            Column() {
                Text(text = "125",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 30.dp, start = 15.dp))
                Text(text = "Following",
                    fontSize = 15.sp
                    )
            }
            Column() {
                Text(text = "500",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 30.dp, start = 40.dp))
                Text(text = "Followers",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
            Column() {
                Text(text = "500",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 30.dp, start = 40.dp))
                Text(text = "Contributions",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }

        Text(text = "Working at 6th Floor",
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                .clip(RoundedCornerShape(10.dp))
                .shadow(ambientColor = Color.Blue, elevation = 30.dp),
            elevation = CardDefaults.cardElevation(20.dp)
        ){
            Text(text = "What is Rajveer doing in Ooty",
                modifier = Modifier.padding(top = 10.dp, start = 10.dp))
            Divider(thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp))
            Text(text = "Answer: ",
                modifier = Modifier.padding(start = 10.dp, top=10.dp)
            )
            Text(text = "Hello This is sn  . . . . .: ",
                modifier = Modifier.padding(start = 10.dp, top=10.dp, bottom = 20.dp)
            )

        }
    }
}