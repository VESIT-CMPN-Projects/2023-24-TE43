package com.example.empoweher.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.empoweher.SQLIteDB.Contact
import com.example.empoweher.SQLIteDB.ContactDatabase
import com.example.empoweher.SQLIteDB.getList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ContactsList(){

    val context = LocalContext.current
    val database = Room.databaseBuilder(context, ContactDatabase::class.java,"contacts").build()
    var List by remember { mutableStateOf(emptyList<Contact>())}
    var scope= rememberCoroutineScope()

    LaunchedEffect(Unit){
        scope.launch(Dispatchers.IO) {
            List = database.itemDao().getAllItems().toMutableList()
        }
    }

//    Text(text = List.toString())

    LazyColumn(
        modifier=Modifier.fillMaxWidth(),
        content = {
            items(List){item->
                Contacts(fName = item.firstName, lName =item.lastName , pNum =item.phoneNumber , checked =item.emergency )


            }
        }
    )




}

suspend fun func(database: ContactDatabase){
    database.itemDao().getAllItems().toMutableList()
}

@Composable
fun Contacts(fName: String, lName: String, pNum: String,checked: Boolean){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) ,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {

            }
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()

        ) {
            Text(text = "First Name : "+ fName)
            Text(text = lName)
            Text(text = pNum)
            if (checked==true){
                Text("Emergency Contact")
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun lazy(list: MutableList<Contact>){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val database = Room.databaseBuilder(context, ContactDatabase::class.java,"contacts").build()
        LazyColumn(state = rememberLazyListState(),
            content = {
        items(list){item ->

            val state = rememberDismissState(
                confirmValueChange = {
                    if(it==DismissValue.DismissedToEnd){
                        scope.launch {
                            database.itemDao().deleteContact(item.phoneNumber)
                        }
                    }
                    true
                }
            )

            SwipeToDismiss(
                state = state,
                background = {
                             val color = when(state.dismissDirection){
                                 DismissDirection.StartToEnd-> Color.Red
                                 DismissDirection.EndToStart-> Color.DarkGray
                                 null -> Color.Transparent
                             }
                },
                dismissContent = {
                    Contacts(fName = item.firstName, lName = item.lastName, pNum = item.phoneNumber,checked = item.emergency)
                })

        }
    })
}

//suspend fun getList(database: ContactDatabase){
//    var scope = rememberCoroutineScope()
//    var list = scope.async(context = Dispatchers.IO) {
//        database.itemDao().getAllItems().toMutableList()
//    }
//    Log.d("Hellllo",list.await().toString())
//}

