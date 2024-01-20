package com.example.empoweher.screen.notes

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import com.example.empoweher.R
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

@SuppressLint("ResourceAsColor")
@Composable
fun DetailedNote(noteId:String?="", navigateToNextScreen: (route: String)->Unit, flag: Boolean? =false){

    val auth= FirebaseAuth.getInstance().currentUser?.uid
    var id="PCAPS"
    if (auth!=null){
        id=auth
    }
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
    BitmapImage(bitmap = bmp)


}

@Composable
fun BitmapImage(bitmap: Bitmap) {
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "some useful description",
    )
}