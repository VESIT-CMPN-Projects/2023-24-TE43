package com.example.empoweher.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.empoweher.auth.signin.GoogleAuthUiClient
import com.example.empoweher.screen.app.App
import com.google.android.gms.auth.api.identity.Identity

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            App(
                googleAuthUiClient = googleAuthUiClient,
                lifecycleScope = lifecycleScope,
            )
        }
    }
}