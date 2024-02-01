package com.example.empoweher.activities

import android.Manifest
import android.app.Activity
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.BackoffPolicy
import androidx.work.Data
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.empoweher.R
import com.example.empoweher.workers.SmsWorker
import com.example.empoweher.model.LocationDetails
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import java.util.concurrent.TimeUnit

class LocationActivity : ComponentActivity() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationCallback: LocationCallback
    private var locationRequired: Boolean = false
    private lateinit var periodicWorkRequest: PeriodicWorkRequest
    val channelId = "My_channel"
    val channelName = "My channel name"
    val mContext: Context? = null
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var notificationBuilder: NotificationCompat.Builder
    private lateinit var notification: Notification

    @RequiresApi(Build.VERSION_CODES.Q)
    private val permissions = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.SEND_SMS,
        android.Manifest.permission.POST_NOTIFICATIONS
    )

    override fun onResume() {
        super.onResume()
        if (locationRequired) {
            startLocationUpdates(locationCallback,fusedLocationProviderClient, this@LocationActivity)
        }
    }

    override fun onPause() {
        super.onPause()
        locationCallback?.let {
            fusedLocationProviderClient?.removeLocationUpdates(it)
        }
    }

    fun startLocationUpdates(locationCallback:LocationCallback,fusedLocationProviderClient: FusedLocationProviderClient,context: Context) {
        locationCallback?.let {
            val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
                .setWaitForAccurateLocation(false).setMinUpdateIntervalMillis(10000)
                .setMaxUpdateDelayMillis(100).build()
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
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
            fusedLocationProviderClient?.requestLocationUpdates(
                locationRequest,
                it,
                Looper.getMainLooper()
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        setContent {
            var currentLocation by remember {
                mutableStateOf(LocationDetails(0.toDouble(), 0.toDouble()))
            }
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    for (location in locationResult.locations) {
                        currentLocation = LocationDetails(location.latitude, location.longitude)
                    }
                }
            }
            Surface(modifier = Modifier.fillMaxSize()) {
                LocationScreen(currentLocation)
            }
        }
    }

    private fun showNotification(context: Context, title: String, msg: String) {
        val notificationManager =
            context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        notificationBuilder = NotificationCompat.Builder(context, channelId)
        notificationBuilder.setSmallIcon(R.drawable.alert1)
            .addAction(R.drawable.alert1, "Turn Off", pendingIntent)
            .setContentTitle(title)
            .setContentText(msg)
            .setOngoing(true)
            .setAutoCancel(true)
        notification = notificationBuilder.build()
        notificationManager.notify(100, notification)
    }

    private fun dismissNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
        notificationManager.cancel(100)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Composable
    fun LocationScreen(currentLocation: LocationDetails) {
        val context = LocalContext.current
        val launcherMultiplePermissions = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionMaps ->
            val areGranted = permissionMaps.values.reduce { acc, next -> acc && next }
            if (areGranted) {
                locationRequired = true
                startLocationUpdates(locationCallback,fusedLocationProviderClient,this@LocationActivity)
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.teal_700)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.share_location),
                    contentDescription = "addContact",
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .size(250.dp)
                        .clickable {
                            if (permissions.all {
                                    ContextCompat.checkSelfPermission(
                                        this@LocationActivity,
                                        it
                                    ) == PackageManager.PERMISSION_GRANTED
                                }) {
                                Toast.makeText(
                                    this@LocationActivity,
                                    "Started Location Sharing!!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                startLocationUpdates(locationCallback,fusedLocationProviderClient,this@LocationActivity)
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
                                    showNotification(
                                        this@LocationActivity,
                                        "Location",
                                        "Work Manager is Running......"
                                    )
                                    WorkManager.getInstance(this@LocationActivity)
                                        .enqueue(periodicWorkRequest)
                                }
                            } else {
                                launcherMultiplePermissions.launch(permissions)
                            }

                        },
                    contentScale = ContentScale.FillBounds

                )
                Text(
                    text = "Start Sharing",
                    fontSize = 35.sp,
                    fontFamily = FontFamily(Font(R.font.font1)),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.white),
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.stop_location_sharing),
                    contentDescription = "viewContacts",
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .size(250.dp)
                        .clickable {
                            WorkManager.getInstance(this@LocationActivity).cancelAllWork()
                            SmsWorker.isStopped = true
                            dismissNotification()
                            Toast.makeText(
                                this@LocationActivity,
                                "Stopped Location Sharing!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                    contentScale = ContentScale.FillBounds,

                    )
                Text(
                    text = "Stop Sharing",
                    fontSize = 35.sp,
                    fontFamily = FontFamily(Font(R.font.font1)),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.white),
                    modifier = Modifier.padding(bottom = 20.dp)
                )


            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Composable
    fun LocationSharing(currentLocation: LocationDetails,context: Context,locationCallback: LocationCallback,fusedLocationProviderClient: FusedLocationProviderClient) {
                Toast.makeText(
                    context,
                    "Started Location Sharing!!",
                    Toast.LENGTH_SHORT
                ).show()

                startLocationUpdates(locationCallback, fusedLocationProviderClient,context)
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
//                showNotification(this@LocationActivity, "Location", "Work Manager is Running......")
                    WorkManager.getInstance(this@LocationActivity)
                        .enqueue(periodicWorkRequest)
                    Toast.makeText(context,"Work Manager started",Toast.LENGTH_SHORT).show()
                    Log.d("Hello","Work Manager started")
                }
    }
}