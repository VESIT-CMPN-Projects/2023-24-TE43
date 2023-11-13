package com.example.empoweher.screen

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.example.empoweher.SQLIteDB.Contact
import com.example.empoweher.SQLIteDB.ContactDatabase
import kotlinx.coroutines.launch

@Composable

fun temp2(){
    val context= LocalContext.current
    val db= Room.databaseBuilder(context,ContactDatabase::class.java,"contacts").build()
    val scope = rememberCoroutineScope()
    val contact=Contact(0,"PCAPS","PRADHAN","69",true)
    Button(onClick = {
        scope.launch {
            db.itemDao().insertContact(contact = contact)
        }
    }) {

    }
}