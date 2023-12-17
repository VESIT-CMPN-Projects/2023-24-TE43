package com.example.empoweher.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.empoweher.R
import com.example.empoweher.composables.Exoplayer
import android.net.Uri


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun Home(){
        val context= LocalContext.current
        LaunchedEffect(Unit){
            android.widget.Toast.makeText(context,"WILL BE IMPLEMENTED SOON...", android.widget.Toast.LENGTH_SHORT).show()
        }
    Exoplayer(uri = Uri.parse("android.resource://com.example.empoweher/raw/magic"))
    }