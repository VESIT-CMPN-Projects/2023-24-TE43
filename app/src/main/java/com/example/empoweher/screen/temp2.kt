package com.example.empoweher.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.empoweher.EventCard
import com.example.empoweher.R
import com.example.empoweher.model.DataState
import com.example.empoweher.model.event
import com.example.empoweher.viewmodel.mainviewmodel
import com.google.firebase.database.FirebaseDatabase

@Composable
fun temp2() {
    /*Insert Data Code
    Row {
        Text(text = "Click to insert Data",modifier = Modifier
            .fillMaxSize()
            .clickable() {
                val dbref = FirebaseDatabase
                    .getInstance()
                    .getReference("Event");
//                val id = dbref.push().key!!
                val id="-Nj7rFEHe7-WrCxFI8KZ"
//                val e = event(
//                    id,
//                    "abab",
//                    "cdefgh",
//                    "hello world",
//                    69,
//                    "20/10/2002",
//                    "20/10/2003",
//                    null,
//                    null
//                )
                val e = event(
                    id,
                    "abab",
                    "cdefgh",
                    "hello world",
                    69,
                    "20/10/2002",
                    "20/10/2003",
                    null,
                    null
                )
                dbref
                    .child(id)
                    .child("capacity").setValue(696969);
            })
    }*/

    /*Retrieve Data Code*/
    val viewModel = viewModel {mainviewmodel()}
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
fun ShowLazyList(data: MutableList<event>) {
    val painter = painterResource(id = R.drawable.event)
    LazyColumn{
        items(data){each->
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .width(300.dp)
                    .clickable {

                    },
                ) {
                EventCard(
                    painter = painter,
                    contentDescription = each.description!!,
                    eventTitle = each.eventName!!,
                    eventLocation = each.address!!,
                    eventHost = "praful",
                    eventCapacity = each.capacity
                )
            }
        }
    }
}
