package com.example.empoweher.composables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.empoweher.R
import com.example.empoweher.model.Screen
import com.example.empoweher.screen.Details.converterHeight
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch

@Composable
fun AnswerCard(navigateToNextScreen: (route: String) -> Unit,
               answerId : String?=null,
               userId : String?=null,
               answer:String?=null,
               like:String?=null,
               dislike:String?=null,
               )
{
    var userImage= getInfoUser(thing = "Dp", userId = userId!!)
    var userName= getInfoUser(thing = "name", userId = userId!!)
    var profession= getInfoUser(thing = "designation", userId = userId!!)
    val context=LocalContext.current
    var dp = rememberAsyncImagePainter(model = userImage)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(converterHeight(5, context).dp)
            .clickable {

            },
        shape = RoundedCornerShape(converterHeight(15,context).dp),
    ) {
        Row {
            Box(modifier=Modifier.padding(converterHeight(10,context).dp)) {
                Image(
                    painter = dp,
                    contentDescription = "cd",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(converterHeight(50, context).dp)
                        .clip(CircleShape)
                )
            }
            Column(modifier=Modifier.padding(converterHeight(5,context).dp,converterHeight(10,context).dp)) {
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
        Text(text = answer!!,
            fontFamily = FontFamily(Font(R.font.font1)),
            fontSize = 18.sp,
            modifier=Modifier.padding(start = converterHeight(10,context).dp)
            )
    }
}
@Composable
fun HeartAnimation() {
    val context= LocalContext.current
    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    var enabled by remember {
        mutableStateOf(false)
    }

    val scale = remember {
        Animatable(1f)
    }

    Icon(
        imageVector = Icons.Outlined.Favorite,
        contentDescription = "Like the product",
        tint = if (enabled) Color.Red else Color.LightGray,
        modifier = Modifier
            .scale(scale = scale.value)
            .size(size = converterHeight(56, context).dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                enabled = !enabled
                coroutineScope.launch {
                    scale.animateTo(
                        0.8f,
                        animationSpec = tween(100),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(100),
                    )
                }
            }
    )
}

@Composable
fun getInfoUser(thing:String?,userId: String?): String {
    val dbref = FirebaseDatabase.getInstance().getReference();
    val user=dbref.child("Users").child(userId!!)
    var userValue by remember {
        mutableStateOf("")
    }
    user.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            userValue=snapshot.child(thing!!).getValue(String::class.java).toString();
        }
        override fun onCancelled(error: DatabaseError) {

        }
    })
    return userValue
}