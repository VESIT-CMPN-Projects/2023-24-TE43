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
import androidx.compose.ui.Alignment
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
import com.example.empoweher.screen.Details.converterHeight
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun AskQuestion() {
    val currentFirebaseUser = FirebaseAuth.getInstance().currentUser!!.uid
    val dbref = FirebaseDatabase.getInstance().getReference("Questions");
    var question by remember {
        mutableStateOf("")
    }
    var tag by remember { mutableStateOf("") }
    val scrollForDescription= rememberScrollState(0)
    var mExpanded by remember { mutableStateOf(false) }
    val tags = listOf("Educational", "Defence", "Exploratory", "Discussion","Empowerment", "Others")
    val context = LocalContext.current
    val name= getInfoUser(thing = "name", userId = currentFirebaseUser)
    val designation= getInfoUser(thing = "designation", userId = currentFirebaseUser)
    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.cream))

    ) {
        Spacer(modifier = Modifier.height(converterHeight(20, context).dp))
        Text(
            text = "Ask Your Question",
            fontSize = converterHeight(28, context).sp,
            fontFamily = FontFamily(Font(R.font.font1)),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(converterHeight(10, context).dp))
        Column(modifier=Modifier.padding(converterHeight(10, context).dp)) {
            Text(
                text = "Question",
                fontSize = converterHeight(23, context).sp,
                fontFamily = FontFamily(Font(R.font.font1)),

                )

            OutlinedTextField(
                value = question,
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
                        question = str
                    } else {
                        Toast.makeText(context, "Only 5000 characters Allowed", Toast.LENGTH_SHORT)
                            .show()

                    }

                })
            Spacer(modifier = Modifier.height(converterHeight(20, context).dp))
            Text(
                text = "Question Tag : ",
                fontSize = converterHeight(23, context).sp,
                fontFamily = FontFamily(Font(R.font.font1)),
            )
            OutlinedTextField(
                value = tag,
                onValueChange = { tag = it },
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = { Text("Click On Arrow") },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                }
            )

            // Create a drop-down menu with list of cities,
            // when clicked, set the Text Field text as the city selected
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
                tags.forEach { label ->
                    DropdownMenuItem(
                        text = { Text(label) },
                        onClick = {
                            tag = label
                            mExpanded = false
                        },
                    )
                }
            }
            Spacer(modifier = Modifier.height(converterHeight(30, context).dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    if (question.isNotEmpty() && tag.isNotEmpty()) {
                        val id = dbref.push().key!!
                        val q = Question(id, currentFirebaseUser, question, name, designation, tag)
                        dbref.child(id).setValue(q)
                        question=""
                        tag=""
                        Toast.makeText(context, "Question Asked Successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text(text = "Ask Question")
                }
            }
        }
    }

}