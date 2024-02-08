package com.example.empoweher.screen.ask

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.empoweher.composables.AnswerCard
import com.example.empoweher.composables.EventCard
import com.example.empoweher.composables.SampleText
import com.example.empoweher.composables.getInfo
import com.example.empoweher.model.Answer
import com.example.empoweher.model.DataState
import com.example.empoweher.model.Event
import com.example.empoweher.model.Screen
import com.example.empoweher.screen.Details.converterHeight
import com.example.empoweher.screen.events.LoadingAnimation3
import com.example.empoweher.screen.events.ShowLazyList
import com.example.empoweher.viewmodel.AnswerViewModel
import com.example.empoweher.viewmodel.mainviewmodel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun getInfoQuestion(thing:String?,questionId: String?): String {
    val dbref = FirebaseDatabase.getInstance().getReference();
    val event=dbref.child("Questions").child(questionId!!)
    var questionValue by remember {
        mutableStateOf("")
    }
    event.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            questionValue=snapshot.child(thing!!).getValue(String::class.java).toString();
        }
        override fun onCancelled(error: DatabaseError) {

        }
    })
    return questionValue
}
@Composable
fun Answer(questionId:String?="",navigateToNextScreen: (route: String)->Unit) {
    val context= LocalContext.current
    val viewModel = viewModel { AnswerViewModel(questionId!!)
    }
    val question= getInfoQuestion(thing = "question", questionId = questionId)
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
                        painter = painterResource(id = R.drawable.event_loading_page),
                        contentDescription = "cd"
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                            Text(
                                text = "Answers Loading  ",
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
        is DataState.SuccessAnswer -> {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.teal_450)),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.height(converterHeight(10, LocalContext.current).dp))
                Row(Modifier.fillMaxWidth().padding(converterHeight(20, LocalContext.current).dp), horizontalArrangement = Arrangement.Center) {
                    SampleText(text = question, fontSize = 22, colorResource(id = R.color.white))

                }

                ShowLazyListAnswer(questionId,result.data,navigateToNextScreen)
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top=converterHeight(10, context = context).dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.teal_700),
                    ),
                    shape = RoundedCornerShape(converterHeight(30,context).dp),
                ){
                    FloatingActionButton(
                        modifier= Modifier
                            .align(Alignment.End)
                            .padding(20.dp, 10.dp)
                            .size(50.dp),
                        shape = CircleShape,
                        onClick = {
                            navigateToNextScreen(Screen.GiveAnswer.route + "/" + questionId!!)
                        },
                    ) {
                        Icon(Icons.Filled.Add, "Floating action button.",modifier=Modifier.size(50.dp))
                    }

                }

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
fun ShowLazyListAnswer(questionId:String?,answer: MutableList<Answer>, navigateToNextScreen: (route: String)->Unit) {
    val context=LocalContext.current
    LazyColumn(modifier= Modifier
        .fillMaxHeight(0.9f)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(25.dp))
        .background(colorResource(id = R.color.teal_450))){
        items(answer){each->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(converterHeight(170,context).dp)
                    .padding(converterHeight(5, context).dp)
                    .clickable {

                    },
            ) {
                AnswerCard(
                    answerId=each.answerId!!,
                    userId = each.userId!!,
                    navigateToNextScreen = navigateToNextScreen,
                    answer = each.answer!!,
                    questionId=questionId

                )
            }
        }
    }
}
