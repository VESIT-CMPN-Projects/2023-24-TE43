package com.example.empoweher.composables
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.empoweher.R

@Preview(widthDp = 432, heightDp = 914, showBackground = true)
@Composable
fun AnswerCard(){

    var likes by remember{
        mutableStateOf(0)
    }

    var thumbUp by remember{
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
    ) {

        Row() {
            Image(painter = painterResource(id = R.drawable.alert),
                contentDescription = "alert",
                modifier = Modifier
                    .padding(start = 10.dp, top = 10.dp)
                    .clip(CircleShape)
                    .size(70.dp),
                contentScale = ContentScale.FillBounds)
            Text(text = "Aman Maane",
                fontSize = 30.sp,
                modifier = Modifier.padding(start = 10.dp, top = 15.dp, end = 20.dp),
                fontWeight = FontWeight.Bold
            )
        }

        Text(text = "Working at 6th Floor in VESIT",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 90.dp, end = 20.dp)
                .offset(0.dp, -30.dp)
        )

        Divider(modifier = Modifier
            .fillMaxWidth(0.9f)
            .align(Alignment.CenterHorizontally)
            .offset(0.dp, -10.dp),
            color = Color.Black
        )

        Text(text = "I think Maane should teach all subjects. He is the best sir i have ever seen in my life",
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, end = 20.dp),
            fontSize = 20.sp)

        Row {
            Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "ThumbUp",
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp)
                    .size(30.dp)
                    .clickable {
                        if(thumbUp == false){

                        }
                    }
            )
            Text(text = likes.toString(),
                modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}