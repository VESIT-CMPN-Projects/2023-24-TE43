package com.example.empoweher

import android.app.Notification
import android.content.Context
import android.location.LocationRequest
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.room.Room
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.empoweher.LocationActivity
import com.example.empoweher.SQLIteDB.Contact
import com.example.empoweher.SQLIteDB.ContactDatabase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import kotlinx.coroutines.delay

class SmsWorker(private val context: Context, params: WorkerParameters) : Worker(context, params) {
    var progess="Start Work.."
    var NOTIFICATION_ID=1
    private var locationRequest:LocationRequest?=null
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    companion object{
        var isStopped=false
    }
    init {
        Companion.isStopped=false
    }
    val database = Room.databaseBuilder(context, ContactDatabase::class.java, "contacts").build()
    var List = emptyList<Contact>()
    var smsBody=""
    override fun doWork(): Result {
        var inputData:Data= inputData
        var Location=inputData.getStringArray("Location")
        Log.d("LOCATION IN WORK :", "Latitude : "+(Location?.get(0)) +" Longitude : "+ (Location?.get(1)))
        Log.d("SmsWorker","Success do work called")
        if (Location?.get(0)!="0.0" && Location?.get(1) !="0.0" && Location?.get(0)!=null && Location?.get(1)!=null)
        {
            val smsManager = SmsManager.getDefault()
            List = database.itemDao().getAllItems().toMutableList()
            for (item in List) {
                if(item.emergency){
                    smsBody =
                        "https://www.google.com/maps/search/?api=1&query=${Location?.get(0)},${Location?.get(1)}"
                    smsManager.sendTextMessage(
                        item.phoneNumber,
                        null,
                        smsBody,
                        null,
                        null
                    )
                }
            }
        }
        return Result.success()
    }
}