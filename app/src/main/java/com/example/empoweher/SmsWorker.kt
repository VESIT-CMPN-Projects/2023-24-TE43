package com.example.empoweher

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.empoweher.LocationActivity
import kotlinx.coroutines.delay

class SmsWorker constructor(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        delay(10000)
//        val activity = LocationActivity()
//        (activity as LocationActivity).startLocationUpdates()
        Log.d("CustomWorker","Success")
        return Result.success()
    }
}