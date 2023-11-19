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
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import androidx.room.Room
import com.example.empoweher.R
import com.example.empoweher.SQLIteDB.Contact
import com.example.empoweher.SQLIteDB.ContactDatabase
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            Log.d("ExampleScreen","PERMISSION GRANTED")

        } else {
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
    val database = Room.databaseBuilder(context, ContactDatabase::class.java, "contacts").build()
    var List by remember { mutableStateOf(emptyList<Contact>()) }
    var scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                List = database.itemDao().getAllItems().toMutableList()
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context,
                        android.Manifest.permission.SEND_SMS
                    ) -> {
                        val smsManager: SmsManager = SmsManager.getDefault()
                        for (item in List) {
                            smsManager.sendTextMessage(item.phoneNumber, null, "Hello", null, null)
                        }
                    }
                    else -> {
                        launcher.launch(android.Manifest.permission.SEND_SMS)
                    }
                }
            }
        }) {
            Text(
                text = "Send SMS",
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }
}