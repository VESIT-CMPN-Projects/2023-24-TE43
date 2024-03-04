package com.example.empoweher.activities

import android.app.DownloadManager.Request
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.empoweher.R
import kotlinx.serialization.json.JsonObject

class ExternalApi : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_external_api)

        val data:TextView = findViewById(R.id.data)
        val url="https://worldtimeapi.org/api/timezone/Asia/Kolkata"
        val request = JsonObjectRequest(
            com.android.volley.Request.Method.GET, url, null,
            { response ->
                try {
                    val datetime = response.getString("datetime")
                    data.setText(datetime)
                } catch (e: Exception) {
                }
            }) { }

        Volley.newRequestQueue(this).add(request)



    }
}