@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.empoweher.screen.safety

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R
import com.example.empoweher.activities.LocationActivity
import com.example.empoweher.activities.VideoConferencing
import com.example.empoweher.composables.Exoplayer
import com.example.empoweher.model.Screen
import com.example.empoweher.screen.Details.converterHeight
import com.example.empoweher.screen.Details.converterWidth

@Preview()
@Composable
fun Safety(navigateToNextScreen: (route: String)->Unit) {
    val context= LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_450))

    ) {
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(converterHeight(10,context).dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.teal_200)),
            elevation = CardDefaults.cardElevation(20.dp),
            shape = RoundedCornerShape(25.dp)
        ){

            Text(
                text = "SAFETY FEATURES",
                fontStyle = FontStyle(R.font.font1),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .absoluteOffset(0.dp, 30.dp),
                fontFamily = FontFamily(Font(R.font.font1))
            )

        }
        Card(modifier = Modifier
            .align(Alignment.BottomStart)
            .fillMaxWidth()
            .fillMaxHeight(.75f)
            .padding(10.dp),
            shape = RoundedCornerShape(25.dp),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.white)),
            elevation = CardDefaults.cardElevation(20.dp)) {

            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(Modifier.padding(8.dp), Arrangement.SpaceBetween){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(modifier = Modifier
                            .size(140.dp),colors = CardDefaults.cardColors(
                            containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(20.dp)){
                            Box(modifier = Modifier
                                    .size(converterHeight(140,context).dp)
                                    .clickable {
                                               navigateToNextScreen(Screen.FakeCall.route)
                                    } ) {
                                Exoplayer(uri = Uri.parse("android.resource://com.example.empoweher/raw/fakecall"))
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "FAKE CALL",
                            fontSize = 30.sp,
                            fontFamily = FontFamily(Font(R.font.font1)),
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.teal_450),
                        )
                    }
                    Spacer(modifier = Modifier.width(40.dp))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(modifier = Modifier
                            .size(140.dp),
                            colors = CardDefaults.cardColors(
                            containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(20.dp)){
                            Image(modifier= Modifier
                                .size(140.dp)
                                .clip(RoundedCornerShape(40.dp))
                                .clickable {
                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://www.google.com/maps/search/nearby+police+station/")
                                    ).setPackage("com.google.android.apps.maps")
                                    context.startActivity(intent)
                                },
                                painter = painterResource(id = R.drawable.policestation1) ,
                                contentDescription = "map",
                                contentScale = ContentScale.Fit)

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "POLICE STN",
                            fontSize = 30.sp,
                            fontFamily = FontFamily(Font(R.font.font1)),
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.teal_450),
                        )
                    }

                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(Modifier.padding(8.dp), Arrangement.SpaceBetween){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Card(modifier = Modifier
                            .size(140.dp),colors = CardDefaults.cardColors(
                            containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(20.dp)){
                            Image(modifier= Modifier
                                .size(140.dp)
                                .clip(RoundedCornerShape(40.dp))
                                .clickable {
//                                    val navigate = Intent(context, VideoConferencing::class.java)
//                                    context.startActivity(navigate)
                                    val navigate = Intent(context, LocationActivity::class.java)
                                    context.startActivity(navigate)
                                },
                                painter = painterResource(id = R.drawable.alert1) ,
                                contentDescription = "",
                                contentScale = ContentScale.Fit)

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "ALERT",
                            fontSize = 30.sp,
                            fontFamily = FontFamily(Font(R.font.font1)),
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.teal_450),
                        )
                    }

                    Spacer(modifier = Modifier.width(40.dp))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Card(modifier = Modifier
                            .size(140.dp),colors = CardDefaults.cardColors(
                            containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(20.dp)){

                            Image(modifier= Modifier
                                .size(140.dp)
                                .clip(RoundedCornerShape(40.dp))
                                .clickable {
                                    navigateToNextScreen(Screen.ContactOption.route)
                                },
                                painter = painterResource(id = R.drawable.emergency1) ,
                                contentDescription = "contactOptions",
                                contentScale = ContentScale.Fit)

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "EMERGENCY",
                            fontSize = 30.sp,
                            fontFamily = FontFamily(Font(R.font.font1)),
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.teal_450),
                        )
                    }

                }
            }

        }
    }
}





