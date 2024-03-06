package com.example.empoweher.screen.ask

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.empoweher.composables.EventCard
import com.example.empoweher.composables.Exoplayer
import com.example.empoweher.composables.QuestionCard
import com.example.empoweher.composables.SampleText
import com.example.empoweher.model.DataState
import com.example.empoweher.model.Event
import com.example.empoweher.model.Question
import com.example.empoweher.model.Screen
import com.example.empoweher.screen.Details.converterHeight
import com.example.empoweher.screen.events.LoadingAnimation3
import com.example.empoweher.viewmodel.QuestionViewModel

@Composable
fun Ask(navigateToNextScreen: (route: String)->Unit){

    val viewModel = viewModel { QuestionViewModel() }
    val context= LocalContext.current

    var tag by remember {

        mutableStateOf("all")

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
                        painter = painterResource(id = R.drawable.event_loading_page),
                        contentDescription = "cd"
                    )
                    Spacer(modifier = Modifier.height(50.dp))
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                            Text(
                                text = "Questions Loading ",
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
        is DataState.SuccessQuestion -> {
            Column(
                modifier= Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.cream)),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {


                ShowLazyListQuestion(result.data,navigateToNextScreen)
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = converterHeight(10, context = context).dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(id = R.color.teal_700),
                    ),
                    shape = RoundedCornerShape(converterHeight(30,context).dp),
                ) {
                    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .horizontalScroll(rememberScrollState())
                                .clip(RoundedCornerShape(converterHeight(20, context).dp))
                        ) {

                            TagButton(tag = "all", viewModel = viewModel)
                            TagButton(tag = "Educational", viewModel = viewModel)
                            TagButton(tag = "Exploratory", viewModel = viewModel)
                            TagButton(tag = "Defence", viewModel = viewModel)
                            TagButton(tag = "Discussion", viewModel = viewModel)
                            TagButton(tag = "Empowerment", viewModel = viewModel)
                            TagButton(tag = "Others", viewModel = viewModel)

                        }


                        Spacer(modifier = Modifier.weight(1f))
                        FloatingActionButton(
                            modifier = Modifier
                                .padding(20.dp, 10.dp)
                                .size(50.dp),
                            shape = CircleShape,
                            onClick = {
                                navigateToNextScreen(Screen.AskQuestion.route)
                            },
                        ) {
                            Icon(
                                Icons.Filled.Add,
                                "Floating action button.",
                                modifier = Modifier.size(50.dp)
                            )
                        }

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
fun ShowLazyListQuestion(event: MutableList<Question>, navigateToNextScreen: (route: String)->Unit) {
    LazyColumn(modifier= Modifier
        .clip(shape = RoundedCornerShape(25.dp))
        .fillMaxHeight(0.9f)
        .fillMaxWidth()){
        items(event){each->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(converterHeight(170, LocalContext.current).dp)
                    .padding(converterHeight(5, LocalContext.current).dp)
                    .clickable {

                    },
            ) {
                QuestionCard(
                    questionId=each.questionId!!,
                    question = each.question!!,
                    userId = each.userId!!,
                    userName=each.userName!!,
                    profession=each.designation!!,
                    navigateToNextScreen = navigateToNextScreen,
                )
            }
        }
    }
}

@Composable
fun TagButton(tag:String,viewModel: QuestionViewModel){
    Button(onClick = {
        viewModel.fetch(tag)

    },
        modifier = Modifier.padding(start = converterHeight(5, LocalContext.current).dp)
    ) {
        SampleText(text = tag,16, colorResource(id = R.color.white))
    }

}

