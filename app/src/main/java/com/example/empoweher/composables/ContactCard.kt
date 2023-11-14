package com.example.empoweher.composables

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.empoweher.R

//@Preview(showBackground = true, heightDp = 790, widthDp = 400, showSystemUi = true)
@Composable
fun ContactCard(fName: String, lName: String, pNum: String,checked: Boolean) {
    Card(
        modifier = Modifier.fillMaxSize()
            .padding(8.dp)
            .background(color = colorResource(id = R.color.mauve)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()

        ) {
            Text(text = "Name : "+ fName +" "+lName)
            Text(text = "Contact Number : "+pNum)
            if (checked){
                Text("Emergency Contact : Yes")
            }
            else{
                Text(text = "Emergency Contact : No")
            }

        }
    }
}