package com.example.empoweher.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.Espresso
import com.example.empoweher.R
import com.example.empoweher.activities.ui.theme.EmpoweHerTheme
import com.example.empoweher.composables.NoteCard
import com.example.empoweher.model.DataState
import com.example.empoweher.model.Note
import com.example.empoweher.model.Screen
import com.example.empoweher.screen.Details.converterHeight
import com.example.empoweher.screen.events.LoadingAnimation3
import com.example.empoweher.screen.home.ShowLazyListNote
import com.example.empoweher.viewmodel.NoteViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class HiddenNotes : FragmentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var flag by remember { mutableStateOf(true) }
            if (flag){
                Greeting(name = "Authorization Needed",a={
                    flag=it
                })

            }
            else{
                val context= LocalContext.current
                val auth= FirebaseAuth.getInstance().currentUser?.uid
                var id="PCAPS"
                if (auth!=null){
                    id=auth
                }

                val calender= Calendar.getInstance()
                var startDate by remember {
                    mutableLongStateOf(0) // or use mutableStateOf(calendar.timeInMillis)
                }
                val viewModel = viewModel { NoteViewModel(userId = id, mode = 1) }
                when( val result= viewModel.response.value){
                    is DataState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(colorResource(id = R.color.cream)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(modifier= Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                                Image(
                                    painter = painterResource(id = R.drawable.notes_loading),
                                    contentDescription = "cd"
                                )
                                Spacer(modifier = Modifier.height(50.dp))
                                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                                        Text(
                                            text = "Notes Loading  ",
                                            fontSize = converterHeight(25, context =context).sp,
                                            textAlign = TextAlign.Center,
                                            fontWeight= FontWeight.Bold,
                                            fontFamily = FontFamily(Font(R.font.font1))
                                        )
                                        LoadingAnimation3()
                                    }
                                }
                            }
                        }
                    }
                    is DataState.SuccessNote -> {
                        Column(
                            modifier= Modifier
                                .fillMaxSize()
                                .background(colorResource(id = R.color.cream)),
                            horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            ShowLazyListNoteHidden(result.data)
//                    Spacer(modifier = Modifier.height(20.dp))


                            val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calender.timeInMillis)
                            var showDatePicker by remember {
                                mutableStateOf(false)
                            }
                            if (showDatePicker) {
                                DatePickerDialog(
                                    onDismissRequest = {
                                        showDatePicker = false
                                    },
                                    confirmButton = {
                                        TextButton(onClick = {
                                            showDatePicker = false
                                            startDate = datePickerState.selectedDateMillis!!
                                            Log.d("kkk",startDate.toString())
                                        }) {
                                            Text(text = "Confirm")
                                        }
                                    },
                                    dismissButton = {
                                        TextButton(onClick = {
                                            showDatePicker = false
                                        }) {
                                            Text(text = "Cancel")
                                        }
                                    }
                                ) {
                                    DatePicker(
                                        state = datePickerState
                                    )
                                }
                            }
                        }

                    }
                    is DataState.Failure -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = result.message,
                                fontSize = converterHeight(30, LocalContext.current).sp,
                            )
                        }
                    }
                    else -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Error Fetching data",
                                fontSize = converterHeight(30, LocalContext.current).sp,
                            )
                        }
                    }
                }



            }

        }
    }
}
@Composable
fun Greeting(name: String,a:(Boolean)->Unit) {
    val context = LocalContext.current
    val activity = LocalContext.current as FragmentActivity
    val executor = ContextCompat.getMainExecutor(activity)


    var promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Authentication Required")
        .setSubtitle("Unlock With Your FingerPrint")
        .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
        .build()

    val biometricPrompt = BiometricPrompt(activity, executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(
                    context,
                    "error",
                    Toast.LENGTH_SHORT
                ).show()
                Espresso.pressBackUnconditionally()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                a(false)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(
                    context, "failed", Toast.LENGTH_LONG

                ).show()
            }
        }
    )

    LaunchedEffect(Unit) {
        biometricPrompt.authenticate(promptInfo)
    }

    Text(text = "hello, $name!")

}

@Composable
fun ShowLazyListNoteHidden(note: MutableList<Note>) {
    val chatListState = rememberLazyListState()

    LaunchedEffect(note){
        chatListState.animateScrollToItem(chatListState.layoutInfo.totalItemsCount)
    }

    LazyColumn(
        modifier= Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        reverseLayout = true,
        state = chatListState,
    ){
        items(note){each->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(converterHeight(175, LocalContext.current).dp)
                    .padding(converterHeight(5, LocalContext.current).dp)
                    .clickable {

                    },
            ) {
                NoteCardHidden(
                    each
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCardHidden(note: Note) {
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
        onClick = {

        }
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




