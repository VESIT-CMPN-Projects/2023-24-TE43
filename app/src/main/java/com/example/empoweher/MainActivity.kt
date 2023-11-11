package com.example.empoweher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.example.empoweher.auth.signin.GoogleAuthUiClient
import com.example.empoweher.model.event
import com.example.empoweher.screen.App
import com.example.empoweher.viewmodel.mainviewmodel
import com.google.android.gms.auth.api.identity.Identity

class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

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