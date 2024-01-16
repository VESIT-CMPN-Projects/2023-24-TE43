package com.example.empoweher.screen.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.empoweher.R
import com.example.empoweher.composables.HeartAnimation
import com.example.empoweher.composables.NoteCard
import com.example.empoweher.composables.QuestionCard
import com.example.empoweher.model.DataState
import com.example.empoweher.model.Note
import com.example.empoweher.model.Question
import com.example.empoweher.screen.events.LoadingAnimation3
import com.example.empoweher.viewmodel.NoteViewModel
import com.example.empoweher.viewmodel.QuestionViewModel
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun Home(navigateToNextScreen: (route: String)->Unit) {
        val auth= FirebaseAuth.getInstance().currentUser?.uid
        var id="PCAPS"
        if (auth!=null){
            id=auth
        }
        val viewModel = viewModel { NoteViewModel(userId = id, mode = 0) }
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
                                    fontSize = 25.sp,
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
                        .background(colorResource(id = R.color.cream)),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    ShowLazyListNote(result.data,navigateToNextScreen)
                }

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
fun ShowLazyListNote(note: MutableList<Note>, navigateToNextScreen: (route: String)->Unit) {
    LazyColumn(modifier= Modifier
        .fillMaxHeight(0.8f)
        .fillMaxWidth()){
        items(note){each->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .padding(5.dp)
                    .clickable {

                    },
            ) {
                NoteCard(
                    each
                )
            }
        }
    }
}

    


