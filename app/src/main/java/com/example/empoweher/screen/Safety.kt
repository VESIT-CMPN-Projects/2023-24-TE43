@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.empoweher.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R
import com.example.empoweher.model.Screen

@Preview(showBackground = true, heightDp = 790, widthDp = 400, showSystemUi = true)
@Composable
fun Safety(navigateToNextScreen: (route: String)->Unit) {
    Box()
    {
    Text(
        text = "Safety Features",
        textAlign = TextAlign.Center,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 40.dp, start = 40.dp),
        color=Color(0xffffA500),
        fontFamily = FontFamily(
            Font(R.font.font1)
        )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.padding(8.dp), Arrangement.SpaceBetween) {
            Column {
                Box(modifier = Modifier
                    .size(180.dp)
                    .border(5.dp, Color(R.color.pink))
                    .clickable {
                        navigateToNextScreen(Screen.FakeCall.route)
                    }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.fakecall),
                        contentDescription = "FakeCall",
                        contentScale = ContentScale.Fit, modifier = Modifier.clip(CircleShape)
                    )
                }
                Text(
                    text = "Fake Call",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 20.sp, fontFamily = FontFamily(
                        Font(R.font.font1)
                    )
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                val context= LocalContext.current
                Box(modifier = Modifier
                    .border(5.dp, Color(R.color.violet))
                    .size(180.dp)
                    .clickable {
//                    navigateToNextScreen(/*Screen.ScreenName.route*/)
                    }) {
                    Image(
                        painter = painterResource(id = R.drawable.police),
                        contentDescription = "Police",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.clickable {
                            val intent= Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.google.com/maps/search/nearby+police+station/"),
                            ).setPackage("com.google.android.apps.maps")
                            context.startActivity(intent)

                        }

                    )
                }
                Text(
                    text = "Police",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.font1)
                    )
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Row(Modifier.padding(8.dp), Arrangement.SpaceEvenly) {
            Column {
                Box(modifier = Modifier
                    .size(180.dp)
                    .border(5.dp, Color(R.color.violet))) {
                    Image(
                        painter = painterResource(id = R.drawable.alert),
                        contentDescription = "Alert",
                        contentScale = ContentScale.Fit, modifier = Modifier.clip(CircleShape)
                    )
                }
                Text(
                    text = "Alert",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.font1)
                    )
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .border(5.dp, Color(R.color.pink))
                        .clickable {
                            navigateToNextScreen(Screen.EmergencyList.route)
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.emergency),
                        contentDescription = "Emergency",
                        contentScale = ContentScale.Fit, modifier = Modifier.clip(CircleShape)
                    )
                }
                Text(
                    text = "Emergency",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(
                        Font(R.font.font1)
                    )
                )
            }
        }
    }
    }
}




