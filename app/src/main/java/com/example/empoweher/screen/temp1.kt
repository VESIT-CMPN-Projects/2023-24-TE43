package com.example.empoweher.screen

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.telephony.SmsManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.empoweher.EventCard
import com.example.empoweher.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

@Composable

fun temp1(){
    MainApp()

}
@Composable
fun MainApp() {
    val context= LocalContext.current
    val db= FirebaseDatabase.getInstance()
    val storage=FirebaseStorage.getInstance()
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        selectedImage = uri
        val ref= storage.getReference()
            .child("image")
        ref.putFile(uri!!).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {
                db.getReference().child("image").setValue(uri.toString()).addOnSuccessListener {
                    Toast.makeText(context,"Photo Added",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    val painter = rememberAsyncImagePainter(selectedImage)
    Column {
        Button(onClick = {
            launcher.launch("image/*")
        }) {
            Text(text = "CLICK")
        }
        Image(painter = painter, contentDescription = "cd")
    }




}



