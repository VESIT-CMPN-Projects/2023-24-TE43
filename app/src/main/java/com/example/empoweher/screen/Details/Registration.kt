package com.example.empoweher.screen.Details

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R
import com.example.empoweher.model.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun Registration(navigateToNextScreen: (route: String)->Unit){
    val context= LocalContext.current
    var username by remember{
        mutableStateOf(" ")
    }
    var password by remember{
        mutableStateOf(" ")
    }
    Column(modifier=Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text(text = "Register Yourself",  modifier = Modifier
            .fillMaxWidth()
            .padding(top = converterHeight(70, context).dp),
            fontSize = converterHeight(25,context).sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.font1))
        )
        OutlinedTextField(value = username, onValueChange = {
           username=it
        }, placeholder = { Text(text ="Enter Your Username")})
        OutlinedTextField(value = password, onValueChange = {
           password=it
        }, placeholder = { Text(text = "Enter Your Password")})
        Button(onClick = {
            if (username.isNotEmpty() && password.isNotEmpty()) {
                val currentFirebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
                val dbref = FirebaseDatabase.getInstance().getReference("Users");
                dbref.child(currentFirebaseUser).child("username").setValue(username)
                dbref.child(currentFirebaseUser).child("password").setValue(password)
                navigateToNextScreen(Screen.Home.route)
            }
            else{
                Toast.makeText(context, "Above Fields Cannot Be Empty", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Register Yourself")
        }
    }
}