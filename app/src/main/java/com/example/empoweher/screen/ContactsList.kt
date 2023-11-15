package com.example.empoweher.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.example.empoweher.SQLIteDB.Contact 
import com.example.empoweher.SQLIteDB.ContactDatabase
import com.example.empoweher.SQLIteDB.getList
import kotlinx.coroutines.CoroutineScope
import com.example.empoweher.composables.ContactCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import com.example.empoweher.R
import com.example.empoweher.model.Screen

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ContactsList(navigateToNextScreen: (route: String)->Unit) {

    val context = LocalContext.current
    val database = Room.databaseBuilder(context, ContactDatabase::class.java, "contacts").build()
    var List by remember { mutableStateOf(emptyList<Contact>()) }
    var scope = rememberCoroutineScope()
    var key by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(key % 2 == 0) {
        scope.launch(Dispatchers.IO) {
            List = database.itemDao().getAllItems().toMutableList()
        }
    }
    if (List.isEmpty()){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.cream)))
        {
            Spacer(modifier = Modifier.height(70.dp))
            Text(text = "No Contacts Saved!!",modifier=Modifier.fillMaxWidth(),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.font1)))
            Text(
                    text = "Please Click Below",
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentHeight()
                        .clickable
                        {
                            navigateToNextScreen(Screen.Temp2.route)
                        },
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.font1))
                )
        }
    }else {
        Button(onClick = {
            navigateToNextScreen(Screen.UpdateContactList.route)
        }) {

        }
        lazy(list = List.toMutableList(), { key++ })
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun lazy(list: MutableList<Contact>,increment:()->Unit){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val database = Room.databaseBuilder(context, ContactDatabase::class.java,"contacts").build()
    LazyColumn(modifier= Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.cream))
        , state = rememberLazyListState(),
            content = { itemsIndexed(items = list, key = { _, listItem ->
                listItem.hashCode()
            }) { index, item ->

            val state = rememberDismissState(
                confirmValueChange = {
                    if(it==DismissValue.DismissedToEnd){
                        Log.d("deleteContact1",item.firstName)
                        scope.launch(Dispatchers.IO) {
                            database.itemDao().deleteContact(item)
                            increment()
                            Log.d("deleteContact2",item.firstName)
                        }
                    }
                    true
                }
            )

            SwipeToDismiss(
                state = state,
                background = {
                             val color = when(state.dismissDirection){
                                 DismissDirection.StartToEnd-> colorResource(id = R.color.redorange)
                                 DismissDirection.EndToStart-> Color.DarkGray
                                 null -> Color.Transparent
                             }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color)
                            .padding(8.dp)
                    ){
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete",
                            modifier = Modifier.align(Alignment.CenterEnd))
                    }
                },
                dismissContent = {
                    ContactCard(fName = item.firstName, lName = item.lastName, pNum = item.phoneNumber,checked = item.emergency)
                },
                directions=setOf(DismissDirection.StartToEnd)
            )
        }
    })
}