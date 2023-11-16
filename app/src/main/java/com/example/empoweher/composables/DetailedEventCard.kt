package com.example.empoweher.composables

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.empoweher.R
import com.example.empoweher.model.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailedEventCard(eventId:String?="") {
    var eventTitle by remember {
        mutableStateOf("")
    }
    var eventDescription by remember {
        mutableStateOf("")
    }
    var eventAddress by remember {
        mutableStateOf("")
    }
    var eventTag by remember {
        mutableStateOf("")
    }
    var eventStartDate by remember {
        mutableStateOf("")
    }
    var eventEndDate by remember {
        mutableStateOf("")
    }
    var timing by remember {
        mutableStateOf("")
    }
    var duration by remember {
        mutableStateOf("")
    }
    var capacity by remember {
        mutableStateOf("")
    }
    var contact by remember {
        mutableStateOf("")
    }
    var city by remember {
        mutableStateOf("")
    }
    var eventCost by remember {
        mutableStateOf("")
    }
    var eventImage by remember {
        mutableStateOf("")
    }
    eventTitle= getInfo("eventName", eventId)
    eventAddress= getInfo("address", eventId)
    eventDescription= getInfo("description", eventId)
    eventTag= getInfo("tag", eventId)
    duration= getInfo("duration", eventId)
    eventStartDate= getInfo("startDate", eventId)
    eventEndDate= getInfo("endDate", eventId)
    city= getInfo("city", eventId)
    eventCost  = getInfo("eventCost", eventId)
    eventImage= getInfo("eventImage", eventId)
    timing= getInfo("timing",eventId)
    capacity= getInfo("capacity",eventId)
    contact=getInfo("contact",eventId)
    var painter= rememberAsyncImagePainter(model = eventImage)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.mauve),
        )
    ){
        Column(modifier=Modifier.fillMaxSize()){
            Text(text = eventTitle,modifier=Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold,fontSize=40.sp, textAlign = TextAlign.Center)
            Image(painter = painter, contentDescription = "cd")
            Text(text = eventDescription)
            Text(text = eventAddress)
            Text(text = eventTag)
            Text(text = duration)
            Text(text = eventStartDate)
            Text(text = eventEndDate)
            Text(text = capacity)
            Text(text = eventCost)
            Text(text = timing)
            Text(text = city)
        }
    }
}

@Composable
fun getInfo(thing:String?,eventId: String?): String {
    val dbref = FirebaseDatabase.getInstance().getReference();
    val event=dbref.child("Event").child(eventId!!)
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