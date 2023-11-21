package com.example.empoweher

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.BackoffPolicy
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.empoweher.model.LocationDetails
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import java.util.concurrent.TimeUnit


class LocationActivity : ComponentActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var locationRequired: Boolean = false
    private lateinit var  periodicWorkRequest:PeriodicWorkRequest
    val channelId = "My_channel"
    val channelName= "My channel name"
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationBuilder: NotificationCompat.Builder
    private lateinit var notification:Notification
    @RequiresApi(Build.VERSION_CODES.Q)
    private val permissions=arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.SEND_SMS,
        android.Manifest.permission.POST_NOTIFICATIONS
    )
    override fun onResume() {
        super.onResume()
        if (locationRequired){
            startLocationUpdates()
        }
    }

    override fun onPause() {
        super.onPause()
        locationCallback?.let {
            fusedLocationProviderClient?.removeLocationUpdates(it)
        }
    }
    fun startLocationUpdates() {
        locationCallback?.let{
            val locationRequest=LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,10000).setWaitForAccurateLocation(false).setMinUpdateIntervalMillis(10000).setMaxUpdateDelayMillis(100).build()
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SEND_SMS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            fusedLocationProviderClient?.requestLocationUpdates(locationRequest,it, Looper.getMainLooper())
        }
    }
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)

        setContent {
            var currentLocation by remember {
                mutableStateOf(LocationDetails(0.toDouble(),0.toDouble()))
            }
            locationCallback=object:LocationCallback(){
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations){
                        currentLocation= LocationDetails(location.latitude,location.longitude)
                    }
                }
            }
            Surface(modifier=Modifier.fillMaxSize()){
                LocationScreen(currentLocation)
            }
        }
    }

    private fun showNotification(context: Context, title:String, msg:String){
        val notificationManager = context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        notificationBuilder = NotificationCompat.Builder(context, channelId)
        notificationBuilder.setSmallIcon(R.drawable.alert1)
            .addAction(R.drawable.alert1, "Turn Off",pendingIntent)
            .setContentTitle(title)
            .setContentText(msg)
            .setOngoing(true)
            .setAutoCancel(true)
        notification=notificationBuilder.build()
        notificationManager.notify(100,notification)
    }

    private fun dismissNotification(){
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
        notificationManager.cancel(100)
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    @Composable
    fun LocationScreen(currentLocation:LocationDetails){
        val context= LocalContext.current
        val launcherMultiplePermissions = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
                permissionMaps->
            val areGranted=permissionMaps.values.reduce { acc, next -> acc && next }
            if (areGranted){
                locationRequired=true
                startLocationUpdates()
                Toast.makeText(context,"Permission Granted",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"Permission Denied",Toast.LENGTH_SHORT).show()
            }
        }
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Your Location :\nLatitude is ${currentLocation.latitude}\nLongitude is ${currentLocation.longitude}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Button(onClick = {
                    if (permissions.all {
                            ContextCompat.checkSelfPermission(
                                this@LocationActivity,
                                it
                            ) == PackageManager.PERMISSION_GRANTED
                        }) {
                        Toast.makeText(
                            this@LocationActivity,
                            "Starting Periodic Work!!",
                            Toast.LENGTH_SHORT
                        ).show()
                        WorkManager.getInstance(this@LocationActivity).cancelAllWork()
                        startLocationUpdates()
                        if (currentLocation.latitude.toString() != "0.0" && currentLocation.longitude.toString() != "0.0" && currentLocation.latitude != null && currentLocation.longitude != null) {
                            val location = arrayOf(
                                currentLocation.latitude.toString(),
                                currentLocation.longitude.toString()
                            )
                            val data: Data =
                                Data.Builder().putStringArray("Location", location).build()
                            periodicWorkRequest =
                                PeriodicWorkRequest.Builder(
                                    SmsWorker::class.java,
                                    15,
                                    TimeUnit.MINUTES
                                )
                                    .addTag("Location")
                                    .setBackoffCriteria(
                                        BackoffPolicy.LINEAR,
                                        PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
                                        TimeUnit.MILLISECONDS
                                    ).setInputData(data).build()
                            showNotification(this@LocationActivity,"Location","Work Manager is Running......")
                            WorkManager.getInstance(this@LocationActivity)
                                .enqueue(periodicWorkRequest)
                        }
                    }
                    else {
                        launcherMultiplePermissions.launch(permissions)
                    }
                },modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Start Work Manager", textAlign = TextAlign.Center)
                }
                Button(onClick = {
                    WorkManager.getInstance(this@LocationActivity).cancelAllWork()
                    SmsWorker.isStopped = true
                    Log.d("TAGAGAGAG", "Stopped Periodic Work!!")
                    dismissNotification()
                    Toast.makeText(
                        this@LocationActivity,
                        "Stopped Periodic Work!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Stop Work Manager", textAlign = TextAlign.Center)
                }
            }
        }
    }