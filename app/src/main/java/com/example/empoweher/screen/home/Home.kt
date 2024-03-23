package com.example.empoweher.screen.home

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.empoweher.R
import com.example.empoweher.activities.HiddenNotes
import com.example.empoweher.composables.NoteCard
import com.example.empoweher.model.DataState
import com.example.empoweher.model.Note
import com.example.empoweher.model.Screen
import com.example.empoweher.screen.Details.converterHeight
import com.example.empoweher.screen.events.LoadingAnimation3
import com.example.empoweher.viewmodel.NoteViewModel
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun Home(navigateToNextScreen: (route: String)->Unit) {

        val systemUiController: SystemUiController = rememberSystemUiController()
        systemUiController.isStatusBarVisible = false
        val context= LocalContext.current
        val auth= FirebaseAuth.getInstance().currentUser?.uid
        var id="PCAPS"
        val time=System.currentTimeMillis()

        if (auth!=null){
            id=auth
        }

        val calender=Calendar.getInstance()
        var startDate by remember {
            mutableLongStateOf(0) // or use mutableStateOf(calendar.timeInMillis)
        }
        val viewModel = viewModel { NoteViewModel(userId = id, mode = 0) }

        LaunchedEffect(key1 = Unit) {
            viewModel.fetch(0)
        }
        when( val result= viewModel.response.value){
            is DataState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.cream)),
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier= Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.notes_loading),
                            contentDescription = "cd"
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                                Text(
                                    text = "Notes Loading  ",
                                    fontSize = converterHeight(25, context =context).sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight= FontWeight.Bold,
                                    fontFamily = FontFamily(Font(R.font.font1))
                                )
                                LoadingAnimation3()
                            }
                        }
                    }
                }
            }
            is DataState.SuccessNote -> {

                Column(
                    modifier= Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.black)),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    ShowLazyListNote(result.data,navigateToNextScreen)
//                    Spacer(modifier = Modifier.height(20.dp))

                    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calender.timeInMillis)
                    var showDatePicker by remember {
                        mutableStateOf(false)
                    }
                    if (showDatePicker) {
                        DatePickerDialog(
                            onDismissRequest = {
                                showDatePicker = false
                            },
                            confirmButton = {

                                TextButton(onClick = {

                                        showDatePicker = false
                                        startDate = datePickerState.selectedDateMillis?:System.currentTimeMillis()
                                        viewModel.fetch(startDate)


                                }) {
                                    Text(text = "Confirm")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = {
                                    showDatePicker = false
                                }) {
                                    Text(text = "Cancel")
                                }
                            }
                        ) {
                            DatePicker(
                                state = datePickerState
                            )
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxSize(),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.teal_700),
                        ),
                        shape = RoundedCornerShape(converterHeight(30,context).dp),
                    ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {
                                context.startActivity(Intent(context, HiddenNotes::class.java)) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Black
                                ),
                                modifier = Modifier.padding(converterHeight(10,context).dp)
                            ) {
                                Text(
                                    text = "Hidden Notes",
                                    fontSize = converterHeight(18, context).sp,
                                    fontFamily = FontFamily(Font(R.font.font1)),
                                )

                            }
                            val scope= rememberCoroutineScope()
                            val scoped= rememberCoroutineScope()
                            Button(
                                onClick = {
                                    showDatePicker = true
                                }
                            ) {
                                Text(text = "Pick Date")
                            }
                            Spacer(Modifier.weight(1f))
                            FloatingActionButton(
                                modifier= Modifier
                                    .padding(
                                        converterHeight(10, context = context).dp,
                                        converterHeight(10, context = context).dp
                                    )
                                    .size(converterHeight(50, context = context).dp),
                                shape = CircleShape,
                                containerColor = Color.Black,
                                contentColor = Color.White,
                                onClick = {
                                    navigateToNextScreen(Screen.CreateNote.route)
                                },
                            ) {
                                Icon(Icons.Filled.Add, "Floating action button.",modifier=Modifier.size(converterHeight(40, context =context).dp))
                            }

                        }



                    }


                }
                Log.d("Response Time In Milliseconds","Response Time In Milliseconds: "+(System.currentTimeMillis()-time).toString())

            }
            is DataState.Failure -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = result.message,
                        fontSize = converterHeight(30, LocalContext.current).sp,
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
                        fontSize = converterHeight(30, LocalContext.current).sp,
                    )
                }
            }
        }
}

@Composable
fun ShowLazyListNote(note: MutableList<Note>, navigateToNextScreen: (route: String)->Unit) {
    val chatListState = rememberLazyListState()

    LaunchedEffect(note){
        chatListState.animateScrollToItem(chatListState.layoutInfo.totalItemsCount)
    }

    LazyColumn(
        modifier= Modifier
            .fillMaxHeight(0.825f)
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(
                    converterHeight(
                        30,
                        context = LocalContext.current
                    ).dp
                )
            ),
        reverseLayout = true,
        state = chatListState,

    ){
        items(note){each->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(converterHeight(175, LocalContext.current).dp)
                    .padding(converterHeight(5, LocalContext.current).dp)
                    .clickable {

                    },
            ) {
                NoteCard(
                    each,navigateToNextScreen
                )
            }
        }
    }


}


    


