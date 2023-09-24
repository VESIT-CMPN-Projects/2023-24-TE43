@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.empoweher

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
@Preview(showBackground = true, heightDp = 500, widthDp = 300, showSystemUi = true)
@Composable
fun safety(){
    Row (Modifier.padding(8.dp),Arrangement.SpaceEvenly) {

            Column {
                Image(
                    painter = painterResource(id = R.drawable.fakecall),
                    contentDescription = "FakeCall",
                    Modifier.size(80.dp)
                )
                Text(text = "Fake Call", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            }
                Column{
                    Image(
                        painter = painterResource(id = R.drawable.police),
                        contentDescription = "Police",
                        Modifier.size(80.dp)
                    )
                    Text(
                        text = "Police",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                }

    }
}




