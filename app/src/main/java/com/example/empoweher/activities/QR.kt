package com.example.empoweher.activities

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.content.ContextCompat
import com.example.empoweher.model.Note
import com.example.empoweher.util.GetDataFromFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.coroutines.launch


class QR : ComponentActivity() {
    var textResult = mutableStateOf("")


    var flag=0

    val barcodeLauncher = registerForActivityResult(ScanContract()){

        result->
        if (result.contents==null) {
            Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show()
        }
        else {
                textResult.value=result.contents
        }
    }

    fun showCamera(){
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Scan a QR")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setOrientationLocked(false)
        barcodeLauncher.launch(options)
    }

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        isGranted->
        if(isGranted) {
            showCamera()

        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Button(onClick = {

                    checkCameraPermission(this@QR)

                }) {

                    Text(text = "Scanner")

                }
                Text(text = textResult.value)

            }
            if (textResult.value != "") {
                val auth = FirebaseAuth.getInstance().currentUser?.uid
                var id = "PCAPS"

                if (auth != null) {
                    id = auth
                }

                val time = System.currentTimeMillis()
                val path = "notes/notes/${id}/visible"
                val dbref = FirebaseDatabase.getInstance()
                    .getReference(path)

                val body = GetDataFromFirebase(nodePath = textResult.value, child = "note")
                val name = GetDataFromFirebase(nodePath = textResult.value, child = "name")

                val note = Note(note = body, noteId = time.toString(), name = name)

                if (body.isNotEmpty()&&name.isNotEmpty()) {
                    dbref.child(time.toString()).setValue(note).addOnSuccessListener {
                        Toast.makeText(this, "Successfully Created", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun checkCameraPermission(context: Context) {
        if (ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.CAMERA
        )==PackageManager.PERMISSION_GRANTED){
            showCamera()
        }
        else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)){
            Toast.makeText(this,"Permission",Toast.LENGTH_SHORT).show()
        }
        else{
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }
}
