package com.example.empoweher.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.empoweher.screen.ask.Navigation
import java.util.Random
import java.util.UUID


class VideoConferencing : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            var name by remember{
                mutableStateOf("")
            }
            var meetId by remember{
                mutableStateOf("")
            }
            Column {
                OutlinedTextField(
                    value = meetId,
                    placeholder = { Text("Enter Meeting Id") },
                    onValueChange = { str ->
                            meetId = str
                    })
                OutlinedTextField(
                    value = name,
                    placeholder = { Text("Enter Your Name") },
                    onValueChange = { str ->
                            name = str
                    })
                Button(onClick = {
                    startMeeting(meetId,name)
                }) {
                    Text(text = "Join Meet")
                }
                Button(onClick = {
                    startMeeting(getRandomMeetingId(),name)
                }) {
                    Text(text = "Create Meet")
                }
            }
        }
    }

    private fun getRandomMeetingId(): String {
        val id:StringBuilder=java.lang.StringBuilder()
        while (id.length!=10){
            val random=Random().nextInt(10)
            id.append(random)
        }
        return id.toString()
    }
    private fun startMeeting(meetingId:String, name:String){
        val userID:String=UUID.randomUUID().toString()
        val intent=Intent(this,Navigation::class.java)
        intent.putExtra("meeting_id",meetingId)
        intent.putExtra("name",name)
        intent.putExtra("user_id",userID)
        startActivity(intent)
    }
}
