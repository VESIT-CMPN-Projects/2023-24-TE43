package com.example.empoweher.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.empoweher.R
import com.example.empoweher.model.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun onBoarding(navigateToNextScreen: (route: String)->Unit){

    Column(modifier=Modifier.fillMaxSize()
        .background(colorResource(id = R.color.cream)),
        horizontalAlignment = Alignment.CenterHorizontally) {
        slider()
        Button(onClick = {
            val currentFirebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
            val dbref = FirebaseDatabase.getInstance()
                .getReference("Users");
            dbref.child(currentFirebaseUser).child("onboarded").setValue(true.toString())
            navigateToNextScreen(Screen.Details.route)
                         },
            colors= ButtonDefaults.buttonColors( containerColor = colorResource(id = R.color.lightorange))){
            Text(text = "Get Started",
                fontSize=20.sp)
        }
    }
}