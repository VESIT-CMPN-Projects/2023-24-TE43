package com.example.empoweher.screen.ask

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.empoweher.R
import com.example.empoweher.composables.getInfoUser
import com.example.empoweher.model.Question
import com.example.empoweher.model.Screen
import com.example.empoweher.screen.Details.converterHeight
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun GiveAnswer(navigateToNextScreen: (route: String)->Unit,questionId:String?=""){

    var currentFirebaseUser ="PCAPS"
    try {
        currentFirebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
    }
    catch (e:Exception){

    }
    val dbref = FirebaseDatabase.getInstance().getReference("Questions/${questionId}/answers");
    var answer by remember {
        mutableStateOf("")
    }
    val scrollForDescription= rememberScrollState(0)
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.cream))

    ) {
        Spacer(modifier = Modifier.height(converterHeight(20, context).dp))
        Text(
            text = "Give Answer ",
            fontSize = converterHeight(28, context).sp,
            fontFamily = FontFamily(Font(R.font.font1)),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(converterHeight(10, context).dp))
        Column(modifier= Modifier.padding(converterHeight(10, context).dp)) {
            Text(
                text = "Answer",
                fontSize = converterHeight(23, context).sp,
                fontFamily = FontFamily(Font(R.font.font1)),

                )

            OutlinedTextField(
                value = answer,
                textStyle = LocalTextStyle.current.merge(
                    TextStyle(
                        fontSize = converterHeight(
                            20,
                            context
                        ).sp
                    )
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding()
                    .height(converterHeight(500, context).dp)
                    .verticalScroll(scrollForDescription),
                onValueChange = { str ->
                    if (str.length <= 5000) {
                        answer = str
                    } else {
                        Toast.makeText(context, "Only 5000 characters Allowed", Toast.LENGTH_SHORT)
                            .show()

                    }

                })
            Spacer(modifier = Modifier.height(converterHeight(20, context).dp))

            Spacer(modifier = Modifier.height(converterHeight(30, context).dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    if (answer.isNotEmpty()){
                        val id = dbref.push().key!!
                        val uttar= com.example.empoweher.model.Answer(id,currentFirebaseUser, answer)
                        dbref.child(id).setValue(uttar).addOnSuccessListener {
                            answer=""
                        }


                    }
                    else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }




                }) {
                    Text(text = "Submit")
                }
            }
        }
    }

}