package com.example.empoweher.screen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun temp1(){
    var events by remember {
        mutableStateOf("")
    }
    val storage= FirebaseDatabase.getInstance().getReference().child("Event").child("-NjJ_pJhdZQsPazg5cb3").child("eventImage").addListenerForSingleValueEvent(object:ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            events = snapshot.getValue(
                String::class.java
            )!!
        }
        override fun onCancelled(error: DatabaseError) {

        }
    })
    val painter= rememberAsyncImagePainter(events)
    Image(painter = painter, contentDescription = "cd")
}

//%2F ---->For /
//%3A ----->For :













//val painter = painterResource(id = R.drawable.event)
//        val contentDescription = "Hello"
//        val eventTitle = "Women Awareness Drive"
//        val eventLocation = "Kalyan"
//        val eventHost = "Vaibhav"
//        val eventCost = "899"
//        val scrollState= rememberScrollState()
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier= Modifier
//                .fillMaxSize()
//                .verticalScroll(scrollState)
//        )
//        {
//            for (i in 1..5){
//                Box(
//                    modifier = Modifier
//                        .height(250.dp)
//                        .width(300.dp),
//
//                    ) {
//                    EventCard(
//                        painter = painter,
//                        contentDescription = contentDescription,
//                        eventTitle = eventTitle,
//                        eventLocation = eventLocation,
//                        eventHost = eventHost,
//                        eventCost = eventCost
//                    )
//                }
//                Spacer(modifier = Modifier.height(30.dp))
//            }
//        }