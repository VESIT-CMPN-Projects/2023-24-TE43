package com.example.empoweher.screen.ask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.empoweher.R
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment

class Navigation : AppCompatActivity() {
    private lateinit var meetingTextView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        meetingTextView=findViewById(R.id.meeting_id_textView)
        val meetingId=getIntent().getStringExtra("meeting_id")
        val name=getIntent().getStringExtra("name")
        val userId=getIntent().getStringExtra("user_id")
        meetingTextView.text="Meeting ID : "+meetingId
        addFragment(userId!!,meetingId!!,name!!)
    }
    fun addFragment(userID:String,meetingId: String,name: String) {
        val appID: Long = AppConstants.appId
        val appSign: String = AppConstants.appSign
        val config = ZegoUIKitPrebuiltVideoConferenceConfig()
        config.turnOnCameraWhenJoining=false
        val fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(
            appID,
            appSign,
            userID,
            name,
            meetingId,
            config
        )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitNow()
    }
}