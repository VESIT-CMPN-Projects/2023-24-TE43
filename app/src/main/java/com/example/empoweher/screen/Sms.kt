package com.example.empoweher.screen

import androidx.compose.runtime.Composable
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.util.Log

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.empoweher.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Sms(){

    smsUI()

}



@Composable
fun smsUI() {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            Log.d("ExampleScreen","PERMISSION GRANTED")

        } else {
            // Permission Denied: Do something
            Log.d("ExampleScreen","PERMISSION DENIED")
        }
    }
    val context = LocalContext.current

    val phoneNumber = remember {
        mutableStateOf("")
    }
    val message = remember {
        mutableStateOf("")
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // on below line we are adding a text for heading.
        Text(
            // on below line we are specifying text
            text = "SMS Manager in Android",
            // on below line we are specifying text color,
            // font size and font weight
            color = colorResource(id = R.color.teal_700),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )


        TextField(

            value = phoneNumber.value,

            onValueChange = { phoneNumber.value = it },
            placeholder = { Text(text = "Enter your phone number") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(

            value = message.value,

            onValueChange = { message.value = it },

            placeholder = { Text(text = "Enter your message") },

            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),

            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),

            singleLine = true,
        )
        // on below line adding a spacer.
        Spacer(modifier = Modifier.height(20.dp))
        // on below line adding a button to send SMS
        Button(onClick = {
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.SEND_SMS
                ) -> {
                    // Some works that require permission
                    val smsManager: SmsManager = SmsManager.getDefault()
                    // on below line sending sms
                    smsManager.sendTextMessage("9372547930", null, "Hello", null, null)
                    // on below line displaying
                    // toast message as sms send.
                    Toast.makeText(
                        context,
                        "Message Sent",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {
                    // Asking for permission
                    launcher.launch(android.Manifest.permission.SEND_SMS)
                }
            }

        }) {
            // on below line creating a text for our button.
            Text(
                // on below line adding a text ,
                // padding, color and font size.
                text = "Send SMS",
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }
}