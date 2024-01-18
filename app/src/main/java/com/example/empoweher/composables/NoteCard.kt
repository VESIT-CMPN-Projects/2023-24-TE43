package com.example.empoweher.composables

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R
import com.example.empoweher.model.Note
import com.example.empoweher.screen.Details.converterHeight
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun NoteCard(note: Note) {
    val text=note.note!!
    val id=note.noteId!!
    val name=note.name!!
    val formatter = SimpleDateFormat("dd/MM/yyyy");
    val context= LocalContext.current
    val date = formatter.format( Date(note.noteId!!.toLong()))

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white),
        ),
        shape = RoundedCornerShape(converterHeight(20,context).dp),
    ){
        Row {
            Text(text = name,
                fontSize = converterHeight(18, context = context).sp,
                fontFamily = FontFamily(Font(R.font.font1)),
                modifier = Modifier.padding(start = converterHeight(20,context).dp, top = converterHeight(15,context).dp, end = converterHeight(20,context).dp),
                fontWeight = FontWeight.Bold,
                
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(text = date,
                fontSize = converterHeight(18, context = context).sp,
                fontFamily = FontFamily(Font(R.font.font1)),
                modifier = Modifier.padding(start = converterHeight(10,context).dp, top = converterHeight(15,context).dp, end = converterHeight(20,context).dp),
            )

        }
        Spacer(modifier = Modifier.height(converterHeight(10,context).dp))
        Divider(modifier = Modifier
            .fillMaxWidth(0.9f)
            .align(Alignment.CenterHorizontally)
            .offset(0.dp, converterHeight(-10, context).dp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(converterHeight(10,context).dp))
        Text(text = text,
            fontFamily = FontFamily(Font(R.font.font1)),
            modifier = Modifier.padding(start = converterHeight(15,context).dp, top = converterHeight(10,context).dp, end = converterHeight(20,context).dp),
            fontSize = 20.sp)

    }


}