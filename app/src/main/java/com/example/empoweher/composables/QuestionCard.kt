package com.example.empoweher.composables

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.empoweher.R
import com.example.empoweher.model.Screen
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch


@Composable
fun QuestionCard (navigateToNextScreen:(route:String)->Unit,
                  questionId:String?="",
                  userId:String?="",
                  userName:String?="",
                  question:String?="",
                  profession:String?="")
{
    var dp by remember {
        mutableStateOf("")
    }
    dp= getValue(thing = "Dp", userId = userId)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(5.dp)
            .clickable {
                navigateToNextScreen(Screen.Answer.route + "/" + questionId!!)
            },
        colors =CardDefaults.cardColors(containerColor = colorResource(id = R.color.pale_brown), contentColor = Color.White)
    ){
        Row {
            Box(modifier=Modifier.padding(10.dp)) {
                val painter = rememberAsyncImagePainter(model = dp)
                Image(
                    painter = painter,
                    contentDescription = "cd",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(45.dp)
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

@Composable
fun getValue(thing:String?,userId: String?): String {
    val dbref = FirebaseDatabase.getInstance().getReference();
    val event=dbref.child("Users").child(userId!!)
    var eventValue by remember {
        mutableStateOf("")
    }
    event.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            eventValue=snapshot.child(thing!!).getValue(String::class.java).toString();
        }
        override fun onCancelled(error: DatabaseError) {

        }
    })
    return eventValue
}