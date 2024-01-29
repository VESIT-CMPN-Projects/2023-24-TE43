package com.example.empoweher.screen.notes

import android.annotation.SuppressLint
import android.content.ClipboardManager
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.test.espresso.Espresso
import com.example.empoweher.R
import com.example.empoweher.screen.Details.converterHeight
import com.example.empoweher.util.DeleteFireBaseNode
import com.example.empoweher.util.GetDataFromFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

@SuppressLint("ResourceAsColor")
@Composable
fun DetailedNote(noteId:String?="", navigateToNextScreen: (route: String)->Unit, flag: Boolean? =false){
    var context= LocalContext.current

    val auth= FirebaseAuth.getInstance().currentUser?.uid
    var id="PCAPS"
    if (auth!=null){
        id=auth
    }

    //QR
    val path="notes/notes/${id}/visible/${noteId}"
    val writer= QRCodeWriter()
    val bitMatrix = writer.encode(path,BarcodeFormat.QR_CODE,512,512)
    val width=bitMatrix.width
    val height=bitMatrix.height
    val bmp = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565)
    for (x in 0 until width){
        for (y in 0 until height){
            bmp.setPixel(x,y,if (bitMatrix[x,y]) Color.Black.toArgb() else Color.White.toArgb())
        }
    }


    val clipboardManager: androidx.compose.ui.platform.ClipboardManager = LocalClipboardManager.current





    var note by remember {
        mutableStateOf("")
    }
    note= GetDataFromFirebase(path,"note")

    Column(

        modifier= Modifier
            .padding(converterHeight(10, context).dp)
            .verticalScroll(rememberScrollState())
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            BitmapImage(bitmap = bmp)

            Text(
                "Scan For Sharing", modifier = Modifier
                    .fillMaxWidth()
                    .padding(converterHeight(15, context).dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                    Font(R.font.font1)
                ),
                fontWeight = FontWeight.Bold, fontSize = converterHeight(30,context).sp
            )

        }
        Text(text = note,
            fontFamily = FontFamily(Font(R.font.font1)),
            modifier = Modifier.padding(converterHeight(5,context).dp),
            fontSize = converterHeight(20, context = context).sp)

        Row {

            Button(
                onClick = {
                    clipboardManager.setText(AnnotatedString((note)))
                    Toast.makeText(context,"Note Copied To Clipboard ",Toast.LENGTH_SHORT).show()

                }
            ) {
                Text(text = "COPY",
                    fontFamily = FontFamily(Font(R.font.font1)),
                    fontSize = converterHeight(20, context = context).sp)


            }

            Button(
                onClick = {
                    DeleteFireBaseNode(path)
                    Toast.makeText(context,"Note Deleted... ",Toast.LENGTH_SHORT).show()

                }
            ) {
                Text(text = "DELETE",
                    fontFamily = FontFamily(Font(R.font.font1)),
                    fontSize = converterHeight(20, context = context).sp)


            }

        }




    }


}

@Composable
fun BitmapImage(bitmap: Bitmap) {
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "some useful description",
    )
}