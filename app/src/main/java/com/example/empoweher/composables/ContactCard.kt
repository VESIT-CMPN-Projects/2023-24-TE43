package com.example.empoweher.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.empoweher.R

//@Preview(showBackground = true, heightDp = 790, widthDp = 400, showSystemUi = true)
@Composable
fun ContactCard(fName: String, lName: String, pNum: String,checked: Boolean) {
    if (checked) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.orchid),
            ),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable {

                },

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "Name : " + fName + " " + lName,
                    fontFamily = FontFamily(Font(R.font.font1))
                )
                Text(text = "Contact Number : " + pNum, fontFamily = FontFamily(Font(R.font.font1)))
                Text("Emergency Contact : Yes", fontFamily = FontFamily(Font(R.font.font1)))
            }
        }
    }
    else{
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.mauve)
            ),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable {

                },

            ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(
                    text = "Name : " + fName + " " + lName,
                    fontFamily = FontFamily(Font(R.font.font1))
                )
                Text(text = "Contact Number : " + pNum, fontFamily = FontFamily(Font(R.font.font1)))
                Text("Emergency Contact : No", fontFamily = FontFamily(Font(R.font.font1)))
            }
        }
    }
}