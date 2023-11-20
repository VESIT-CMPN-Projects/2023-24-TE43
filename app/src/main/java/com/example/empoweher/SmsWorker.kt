package com.example.empoweher

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.empoweher.LocationActivity
import kotlinx.coroutines.delay

class SmsWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
//        val activity = LocationActivity()
//        (activity as LocationActivity).startLocationUpdates()
        Log.d("SmsWorker","Success do work called")
        return Result.success()
    }
}