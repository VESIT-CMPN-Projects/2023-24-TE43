package com.example.empoweher.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R
import com.example.empoweher.model.Screen

@Composable
fun QuestionCard (navigateToNextScreen:(route:String)->Unit,
                  questionId:String?="",
                  userName:String?="",
                  question:String?="",
                  profession:String?="")
{
    var color = colorResource(id = R.color.mauve)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(5.dp)
            .clickable {

            },
        colors =CardDefaults.cardColors(containerColor = colorResource(id = R.color.pale_brown), contentColor = Color.White)
    ){
        Row {
            Box(modifier=Modifier.padding(10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.alert),
                    contentDescription = "cd",
                    modifier = Modifier.height(40.dp)
                        .clip(CircleShape)
                )
            }
            Column(modifier=Modifier.padding(2.dp,10.dp)) {
                Text(
                    text = userName!!,
                    fontFamily = FontFamily(Font(R.font.font1)),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = profession!!,
                    fontFamily = FontFamily(Font(R.font.font1)),
                    fontSize = 10.sp
                )
            }
        }
        Text(text = question!!,
            fontFamily = FontFamily(Font(R.font.font1)),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier=Modifier.padding(10.dp,0.dp)
            )
}
}