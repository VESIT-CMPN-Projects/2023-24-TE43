package com.example.empoweher.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyList() {

    val context= LocalContext.current

    val painter = painterResource(id = R.drawable.background_for_form)
    var fName by remember{
        mutableStateOf("")
    }
    var lName by remember{
        mutableStateOf("")
    }
    var pNum by remember{
        mutableStateOf("")
    }
    var checked by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painter,
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()

        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(Modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "First Name: ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    value = fName,
                    onValueChange = { str ->
                        fName = str

                    })


            }
            Spacer(Modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Last Name: ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    value = lName,
                    onValueChange = { str ->
                        lName = str

                    })


            }
            Spacer(Modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Phone: ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.width(48.dp))
                OutlinedTextField(
                    value = pNum,
                    onValueChange = { str ->
                        pNum = str

                    })

            }
            Spacer(Modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Close: ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.width(48.dp))
                Switch(
                    checked = checked,
                    onCheckedChange ={check->
                        checked=check

                    } )
            }
            Spacer(Modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement=Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    Toast.makeText(context,"Hello",Toast.LENGTH_LONG).show()
                }) {
                    Text(text = "SAVE CONTACT")

                }


            }


        }
    }

}

