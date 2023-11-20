package com.example.empoweher

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.empoweher.auth.signin.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import java.time.Duration
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val smsWorkRequest: WorkRequest = OneTimeWorkRequest.Builder(SmsWorker::class.java)
//                .setInitialDelay(1, TimeUnit.MINUTES)
//                .build()
//            Toast.makeText(this, "WorkManagerInitialized", Toast.LENGTH_SHORT).show()
//            // Schedule the WorkRequest with WorkManager
//            WorkManager.getInstance(this).enqueue(smsWorkRequest)
            Surface(modifier=Modifier.fillMaxSize()){
                Column(modifier=Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text("Hello World", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                }
            }
            simpleWork()
        }

//        setContent {
//            LaunchedEffect(key1 = Unit){
//                val workRequest = PeriodicWorkRequestBuilder<SmsWorker>(
//                    repeatInterval = 15,
//                    repeatIntervalTimeUnit = TimeUnit.MINUTES
//                ).setBackoffCriteria(
//                    backoffPolicy = BackoffPolicy.LINEAR,
//                    duration = Duration.ofSeconds(15)
//                ).build()
//                val workManager = WorkManager.getInstance(applicationContext)
//                workManager.enqueue(workRequest)
//            }
//        }
    }

    private fun simpleWork() {
        val mRequest:WorkRequest= OneTimeWorkRequestBuilder<SmsWorker>().build()
        WorkManager.getInstance(this).enqueue(mRequest)
    }

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//
//            App(
//                googleAuthUiClient = googleAuthUiClient,
//                lifecycleScope = lifecycleScope,
//            )
//        }
//    }
}