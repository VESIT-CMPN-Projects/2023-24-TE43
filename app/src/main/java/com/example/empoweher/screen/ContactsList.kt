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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import com.example.empoweher.SQLIteDB.Contact
import com.example.empoweher.SQLIteDB.ContactDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

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
