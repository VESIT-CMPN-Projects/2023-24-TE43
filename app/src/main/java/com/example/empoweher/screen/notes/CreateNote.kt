package com.example.empoweher.screen.notes

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.empoweher.R
import com.example.empoweher.activities.HiddenNotes
import com.example.empoweher.activities.QR
import com.example.empoweher.model.Note
import com.example.empoweher.model.Screen
import com.example.empoweher.screen.Details.converterHeight
import com.example.empoweher.util.BarcodeScanner
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateNote(navigateToNextScreen: (route: String)->Unit) {

    lateinit var barcodeScanner: BarcodeScanner

    val context=LocalContext.current
    var description by remember {
        mutableStateOf("")
    }
    var checked by remember { mutableStateOf(false) }
    var name by remember {
        mutableStateOf("")
    }
    val scrollForDescription= rememberScrollState(0)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(converterHeight(5, context).dp, converterHeight(10, context).dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Create Note:",
            fontSize = converterHeight(23, context).sp,
            fontFamily = FontFamily(Font(R.font.font1)),
            textAlign = TextAlign.Start

            )

        OutlinedTextField(
            value = description,
            textStyle = LocalTextStyle.current.merge(TextStyle(fontSize = converterHeight(20, context).sp)),
            modifier = Modifier
                .fillMaxWidth()
                .padding()
                .height(converterHeight(550, context).dp)
                .verticalScroll(scrollForDescription),
            onValueChange = { str ->
                if(str.length<=50000){
                    description = str
                }
                else{
                    Toast.makeText(context,"Only 50000 characters Allowed", Toast.LENGTH_SHORT).show()

                }

            }
        )

        Spacer(modifier = Modifier.width(converterHeight(40, context).dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(converterHeight(5, context).dp, converterHeight(10, context).dp)

        ) {
            Text(
                text = "Note Name: ",
                fontSize = converterHeight(23, context).sp,
                fontFamily = FontFamily(Font(R.font.font1)),

                )

            OutlinedTextField(
                value = name,
                textStyle = LocalTextStyle.current.merge(TextStyle(fontSize = converterHeight(20, context).sp)),
                onValueChange = { str ->
                    if(str.length<=20){
                        name = str
                    }
                    else{
                        Toast.makeText(context,"Only 20 characters Allowed",Toast.LENGTH_SHORT).show()

                    }

                })
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Hidden: ",
                fontSize = converterHeight(25, context).sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.font1)),
            )
            Spacer(modifier = Modifier.width(converterHeight(10, context).dp))
            Switch(
                checked = checked,
                onCheckedChange = { check ->
                    checked = check

                })

        }
        val time = System.currentTimeMillis()
        val auth= FirebaseAuth.getInstance().currentUser?.uid
        var id="PCAPS"
        if (auth!=null){
            id=auth
        }
        Spacer(modifier = Modifier.width(converterHeight(15, context).dp))
        Button(onClick ={

            if (description.isNotBlank()){
                var path="notes/notes/${id}/visible"
                if (checked){
                    path="notes/notes/${id}/hidden"
                }

                val dbref = FirebaseDatabase.getInstance()
                    .getReference(path)

                val note= Note(note=description, noteId = time.toString(), name = name)
                dbref.child(time.toString()).setValue(note).addOnSuccessListener {
                    Toast.makeText(context,"Successfully Created",Toast.LENGTH_SHORT).show()
                    description=""
                    name=""
                    checked=false
                    val mediaPlayer = MediaPlayer.create(context,R.raw.alert)
                    mediaPlayer.start()
                }


            }



        }) {
            Text(
                text = "Create",
                fontSize = converterHeight(18, context).sp,
                fontFamily = FontFamily(Font(R.font.font1)),
            )

        }

    }




}