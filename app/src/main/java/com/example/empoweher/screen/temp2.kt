package com.example.empoweher.screen

import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.empoweher.EventCard
import com.example.empoweher.MainActivity
import com.example.empoweher.R
import com.example.empoweher.model.DataState
import com.example.empoweher.model.event
import com.example.empoweher.viewmodel.mainviewmodel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun temp2() {
    val viewModel = viewModel { mainviewmodel() }
    when( val result= viewModel.response.value){
        is DataState.Success ->{
            ShowLazyList(result.data)
        }
        else->{

        }
    }

}

@Composable
fun ShowLazyList(data: MutableList<event>) {
    val painter = painterResource(id = R.drawable.event)
    val contentDescription = "Hello"
    val eventTitle = "Women Awareness Drive"
    val eventLocation = "Kalyan"
    val eventHost = "Vaibhav"
    val eventCost = "899"
    LazyColumn{
        items(data){each->
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .width(300.dp),

                ) {
                EventCard(
                    painter = painter,
                    contentDescription = contentDescription,
                    eventTitle = each.name!!,
                    eventLocation = eventLocation,
                    eventHost = eventHost,
                    eventCost = eventCost
                )
            }
        }
    }
}
