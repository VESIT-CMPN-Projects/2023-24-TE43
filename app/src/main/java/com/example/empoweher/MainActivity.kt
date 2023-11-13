package com.example.empoweher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.empoweher.SQLIteDB.ContactDatabase
import com.example.empoweher.SQLIteDB.ContactViewModel
import com.example.empoweher.auth.signin.GoogleAuthUiClient
import com.example.empoweher.screen.App
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint

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