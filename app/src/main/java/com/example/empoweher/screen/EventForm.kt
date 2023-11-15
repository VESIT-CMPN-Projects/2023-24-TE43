package com.example.empoweher.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.empoweher.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventForm(){
    val context= LocalContext.current
    var name by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var address by remember {
        mutableStateOf("")
    }

    val calendar1 = Calendar.getInstance()
    calendar1.set(2023, 11, 22)
    val calendar2 = Calendar.getInstance()
    calendar2.set(2025, 11, 22)
    var startDate by remember {
        mutableLongStateOf(calendar1.timeInMillis) // or use mutableStateOf(calendar.timeInMillis)
    }
    var endDate by remember {
        mutableLongStateOf(calendar2.timeInMillis) // or use mutableStateOf(calendar.timeInMillis)
    }
    val scrollForDescription= rememberScrollState(0)
    val scrollForAddress= rememberScrollState(0)

    var hour by remember {
        mutableStateOf("")
    }
    var second by remember {
        mutableStateOf("")
    }
    var minute by remember {
        mutableStateOf("")
    }
    Box (modifier = Modifier
        .fillMaxSize()

    )
    {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp)
            ) {
                Text(
                    text = "Create Event",
                    fontSize = 35.sp,
                    fontFamily = FontFamily(Font(R.font.font1)),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.purple_200)

                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp)

            ) {
                Text(
                    text = "Event Name: ",
                    fontSize = 23.sp,
                    fontFamily = FontFamily(Font(R.font.font1)),

                )

                OutlinedTextField(
                    value = name,
                    textStyle = LocalTextStyle.current.merge(TextStyle(fontSize = 20.sp)),
                    onValueChange = { str ->
                        if(str.length<=20){
                        name = str
                        }
                        else{
                            Toast.makeText(context,"Only 20 characters Allowed",Toast.LENGTH_SHORT).show()

                        }

                    })
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp)

            ) {
                Text(
                    text = "Event Description:",
                    fontSize = 23.sp,
                    fontFamily = FontFamily(Font(R.font.font1)),

                    )

                OutlinedTextField(
                    value = description,
                    textStyle = LocalTextStyle.current.merge(TextStyle(fontSize = 20.sp)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .height(350.dp)
                        .verticalScroll(scrollForDescription),
                    onValueChange = { str ->
                        if(str.length<=5000){
                            description = str
                        }
                        else{
                            Toast.makeText(context,"Only 5000 characters Allowed",Toast.LENGTH_SHORT).show()

                        }

                    })



            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp)

            ) {
                Text(
                    text = "Event Address:",
                    fontSize = 23.sp,
                    fontFamily = FontFamily(Font(R.font.font1)),

                    )

                OutlinedTextField(
                    value = address,
                    textStyle = LocalTextStyle.current.merge(TextStyle(fontSize = 20.sp)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .height(175.dp)
                        .verticalScroll(scrollForAddress),
                    onValueChange = { str ->
                        if(str.length<=300){
                            address = str
                        }
                        else{
                            Toast.makeText(context,"Only 300 characters Allowed",Toast.LENGTH_SHORT).show()

                        }

                    })
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp)
            ){
                // set the initial date
                val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar1.timeInMillis)

                var showDatePicker by remember {
                    mutableStateOf(false)
                }


                var btnText by remember {
                    mutableStateOf("Select Date")
                }

                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = {
                            showDatePicker = false
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                if(datePickerState.selectedDateMillis!!<=endDate){
                                showDatePicker = false
                                startDate = datePickerState.selectedDateMillis!!
                                }
                                else{
                                    Toast.makeText(context,"Start Date Should Be Earlier Than End Date",Toast.LENGTH_SHORT).show()
                                }
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

                Row (
                    modifier=Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(
                        text = "Start Date: ",
                        fontSize = 23.sp,
                        fontFamily = FontFamily(Font(R.font.font1)),

                        )
                    Spacer(modifier = Modifier.width(65.dp))
                    Button(
                        onClick = {
                            showDatePicker = true
                        }
                    ) {
                        Text(text = btnText)
                    }

                    val formatter = SimpleDateFormat("dd MM yyyy", Locale.ROOT)

                    btnText = formatter.format(Date(startDate)).toString()
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp)
            ){
                val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar2.timeInMillis)
                var showDatePicker by remember {
                    mutableStateOf(false)
                }
                var btnText by remember {
                    mutableStateOf("Select Date")
                }
                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = {
                            showDatePicker = false
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                if (datePickerState.selectedDateMillis!!>=startDate){
                                showDatePicker = false
                                endDate = datePickerState.selectedDateMillis!!
                                }
                                else{
                                    Toast.makeText(context,"End Date Should End After Start Date",Toast.LENGTH_SHORT).show()
                                }
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
                Row (
                    modifier=Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(
                        text = "End Date:   ",
                        fontSize = 23.sp,
                        fontFamily = FontFamily(Font(R.font.font1)),

                        )
                    Spacer(modifier = Modifier.width(65.dp))
                    Button(
                        onClick = {
                            showDatePicker = true
                        }
                    ) {
                        Text(text = btnText)
                    }
                    val formatter = SimpleDateFormat("dd MM yyyy", Locale.ROOT)
                    btnText = formatter.format(Date(endDate)).toString()
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp)
            ) {
                Row(
                    modifier=Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Event Timing: ",
                        fontSize = 23.sp,
                        fontFamily = FontFamily(Font(R.font.font1)),

                        )
                    OutlinedTextField(
                        value = hour,
                        textStyle = LocalTextStyle.current.merge(TextStyle(fontSize = 20.sp)),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .padding(2.dp,0.dp)
                            .width(65.dp),
                        placeholder = { Text("HH") },
                        onValueChange = { str ->
                            if(str.isNotEmpty()){
                            if(Integer.parseInt(str) in 0..23){
                                hour = str
                            }
                            else{
                                Toast.makeText(context,"Enter Valid Hours",Toast.LENGTH_SHORT).show()

                            }
                            }
                            else{
                                hour=""
                            }


                        })
                    OutlinedTextField(
                        value = minute,
                        textStyle = LocalTextStyle.current.merge(TextStyle(fontSize = 20.sp)),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .padding(2.dp,0.dp)
                            .width(65.dp),
                        placeholder = { Text("MM") },
                        onValueChange = { str ->
                            if (str.isNotEmpty()) {
                                if (Integer.parseInt(str) in 0..59) {
                                    minute = str
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Enter Valid Minutes",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            } else {
                                minute = ""
                            }
                        }
                    )
                    OutlinedTextField(
                        value = second,
                        textStyle = LocalTextStyle.current.merge(TextStyle(fontSize = 20.sp)),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .padding(2.dp,0.dp)
                            .width(65.dp),
                        placeholder = { Text("SS") },
                        onValueChange = { str ->
                            if (str.isNotEmpty()) {
                                if (Integer.parseInt(str) in 0..59) {
                                    second = str
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Enter Valid Seconds",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            } else {
                                second = ""
                            }
                        }
                    )

                }

            }

        }

    }
}
