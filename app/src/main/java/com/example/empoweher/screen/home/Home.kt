package com.example.empoweher.screen.home

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R
import com.example.empoweher.auth.signin.TypewriterText
import com.example.empoweher.composables.EventCard
import com.example.empoweher.composables.HeartAnimation
import com.example.empoweher.composables.QuestionCard
import com.example.empoweher.composables.getInfo
import com.example.empoweher.composables.getValue
import com.example.empoweher.composables.onBoarding
import com.example.empoweher.composables.slider
import com.example.empoweher.model.Screen
import com.example.empoweher.screen.Details.converterHeight
import com.example.empoweher.viewmodel.mainviewmodel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun Home(navigateToNextScreen: (route: String)->Unit) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = Color.Black)
        ){
            Box(modifier = Modifier
                .size(converterHeight(70, context).dp)
                .padding(converterHeight(5, context).dp)) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.logo_svg),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(120.dp),
                    contentScale = ContentScale.Crop
                )
            }
            TypewriterText(texts = listOf("Welcome to EmpowerHer"))
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier
                .size(converterHeight(70, context).dp)
                .padding(converterHeight(5, context).dp)) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.profile_svg),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(120.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Box(modifier= Modifier
            .fillMaxWidth()
            .height(converterHeight(400, context).dp)
            .padding(converterHeight(10, context).dp)
            .border(width = 2.dp, color = Color.Black)
        )
        {
            slider()
        }
        Column(modifier= Modifier
            .border(width = 2.dp, color = Color.Black)
            .padding(converterHeight(10, context).dp)
            .height(converterHeight(200, context).dp)
            .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Recent Schemes",
                fontSize = converterHeight(20,context).sp,
                fontFamily = FontFamily(Font(R.font.font1)),
                textAlign = TextAlign.Center,
                modifier=Modifier.fillMaxWidth()
            )
            SchemeCard(schemeName = "Beti Bachao Beti Padhao","https://wcd.nic.in/schemes/beti-bachao-beti-padhao-scheme")
            SchemeCard(schemeName = "One Stop Centre","https://wcd.nic.in/schemes/one-stop-centre-scheme-1")
            SchemeCard(schemeName = "Women Helpline","https://wcd.nic.in/schemes/women-helpline-scheme-2")
            SchemeCard(schemeName = "Pradhan Mantri Matru Vandana Yojana","https://wcd.nic.in/schemes/pradhan-mantri-matru-vandana-yojana")
            SchemeCard(schemeName = "Shakti Sadan","https://wcd.nic.in/schemes/shakti-sadan")
            SchemeCard(schemeName = "Sakhi Niwas - Working Women Hostel","https://wcd.nic.in/schemes/sakhi-niwas-working-women-hostel")
            SchemeCard(schemeName = "Paalna - National Creche Scheme","https://wcd.nic.in/schemes/paalna-national-creche-scheme")
            SchemeCard(schemeName = "Nirbhaya","https://wcd.nic.in/schemes/nirbhaya")
        }
        Column(
            modifier=Modifier.padding(converterHeight(10,context).dp)
                .border(width = 2.dp, color = Color.Black)
        ) {
            Text(text = "Recommended Events",
                fontSize = converterHeight(20,context).sp,
                fontFamily = FontFamily(Font(R.font.font1)),
                textAlign = TextAlign.Center,
                modifier=Modifier.fillMaxWidth()
            )
            val eventId="-Njp7ySPE-z629UhUxVk"
            val eventImage= "https://firebasestorage.googleapis.com/v0/b/empowerher-39d60.appspot.com/o/b0Yra1fWvHbPuGJgsijrfFvHaSD2%2F2023-11-22T09%3A17%3A32.545967?alt=media&token=f58cb4fa-9b31-487f-a59e-b7ca13440502"
            val eventTag= "Empowerment"
            val eventName= "TCS CareerNext"
            val eventCost="250"
            EventCard(navigateToNextScreen = navigateToNextScreen,eventId=eventId,eventCost=eventCost, eventTag = eventTag, eventImage = eventImage, eventTitle = eventName)
        }
        Column(
            modifier=Modifier.padding(converterHeight(10,context).dp)
                .border(width = 2.dp, color = Color.Black)
        ) {
            Text(text = "Top Questions",
                fontSize = converterHeight(20,context).sp,
                fontFamily = FontFamily(Font(R.font.font1)),
                textAlign = TextAlign.Center,
                modifier=Modifier.fillMaxWidth()
            )
            val questionId="-NqBEgazxLJGkzmu2N29"
            val question="how to study AIML ??"
            val designation="student"
            val tag= "Educational"
            val userId="4rqHpl2DhXeU2FX76rf7G9H5nlR2"
            val userName="dhruv"
            QuestionCard(navigateToNextScreen = navigateToNextScreen, questionId = questionId, question = question, profession = designation, userId = userId, userName = userName)
        }
    }
    Box(modifier = Modifier.height(converterHeight(300,context).dp))
}

@Composable
fun SchemeCard(schemeName:String,uriString:String){
    val context= LocalContext.current
    Card(modifier= Modifier
        .fillMaxWidth()
        .padding(converterHeight(5, context).dp),
        colors=CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.lightblue)
        )
        ){
        Text(text = schemeName,
            modifier= Modifier
                .fillMaxWidth()
                .padding(converterHeight(5, context).dp)
                .clickable {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(uriString)
                    )
                    context.startActivity(urlIntent)
                },
            fontSize = converterHeight(17,context).sp,
            fontFamily = FontFamily(Font(R.font.font1)),
            textAlign = TextAlign.Center
        )
    }
}