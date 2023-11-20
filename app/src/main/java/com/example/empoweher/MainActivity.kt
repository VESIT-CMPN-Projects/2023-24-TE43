package com.example.empoweher

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.work.BackoffPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.empoweher.auth.signin.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import java.time.Duration
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
//        setContent {
//            val smsWorkRequest: WorkRequest = OneTimeWorkRequest.Builder(SmsWorker::class.java)
//                .setInitialDelay(1, TimeUnit.MINUTES)
//                .build()
//            Toast.makeText(this, "WorkManagerInitialized", Toast.LENGTH_SHORT).show()
//
//            // Schedule the WorkRequest with WorkManager
//            WorkManager.getInstance(this).enqueue(smsWorkRequest)
//        }

        setContent {
            LaunchedEffect(key1 = Unit){
                val workRequest = PeriodicWorkRequestBuilder<SmsWorker>(
                    repeatInterval = 1,
                    repeatIntervalTimeUnit = TimeUnit.MINUTES
                ).setBackoffCriteria(
                    backoffPolicy = BackoffPolicy.LINEAR,
                    duration = Duration.ofSeconds(15)
                ).build()

                val workManager = WorkManager.getInstance(applicationContext)
                workManager.enqueue(workRequest)
            }
        }
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