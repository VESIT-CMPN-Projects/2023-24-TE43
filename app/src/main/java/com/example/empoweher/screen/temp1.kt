package com.example.empoweher.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.empoweher.EventCard
import com.example.empoweher.model.DataState
import com.example.empoweher.model.Event
import com.example.empoweher.viewmodel.mainviewmodel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun temp1(){
    val viewModel = viewModel { mainviewmodel() }
    when( val result= viewModel.response.value){
        is DataState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is DataState.Success -> {
            ShowLazyList(result.data)
        }
        is DataState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = result.message,
                    fontSize = 30.sp,
                )
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error Fetching data",
                    fontSize = 30.sp,
                )
            }
        }
    }
}

@Composable
fun ShowLazyList(event: MutableList<Event>) {
    LazyColumn{
       items(event){each->
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .width(300.dp)
                    .clickable {

                    },
            ) {
                EventCard(
                    eventTitle = each.eventName!!,
                    eventAddress = each.address!!,
                    eventCity=each.city!!,
                    eventCapacity = each.capacity!!,
                    eventStartDate = each.startDate!!,
                    eventEndDate = each.endDate!!,
                    eventTiming = each.timing!!,
                    eventCost = each.eventCost!!,
                    eventImage = each.eventImage!!
                )
            }
        }
    }
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