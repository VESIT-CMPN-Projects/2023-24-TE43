package com.example.empoweher.screen.home

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.empoweher.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun Home() {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        android.widget.Toast.makeText(
            context,
            "WILL BE IMPLEMENTED SOON...",
            android.widget.Toast.LENGTH_SHORT
        ).show()
    }
    val displayMetrics = context.resources.displayMetrics
    val dpHeight = displayMetrics.heightPixels / displayMetrics.density
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    Log.d("dp",dpHeight.toString())
    Log.d("dp",dpWidth.toString())

    Box(
        Modifier
            .height(1200.dp)
            .width(750.dp)
            .border(BorderStroke(5.dp, colorResource(id = R.color.pale_brown)))
    ) {


    }
}