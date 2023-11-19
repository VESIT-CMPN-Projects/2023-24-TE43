package com.example.empoweher

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.telephony.SmsManager
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.empoweher.SQLIteDB.Contact
import com.example.empoweher.SQLIteDB.ContactDatabase
import com.example.empoweher.screen.LocationDetails
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationActivity : ComponentActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var locationRequired: Boolean = false
    @RequiresApi(Build.VERSION_CODES.Q)
    private val permissions=arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.SEND_SMS
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
    private fun startLocationUpdates() {
        locationCallback?.let{
            val locationRequest=LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,100).setWaitForAccurateLocation(false).setMinUpdateIntervalMillis(1000).setMaxUpdateDelayMillis(100).build()
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
    @RequiresApi(Build.VERSION_CODES.Q)
    @Composable
    fun LocationScreen(currentLocation:LocationDetails){
        val context= LocalContext.current
        val launcherMultiplePermissions = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
                permissionMaps->
            val areGranted=permissionMaps.values.reduce ({ acc, next -> acc && next })
            if (areGranted){
                locationRequired=true
                startLocationUpdates()
                Toast.makeText(context,"Permission Granted",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"Permission Denied",Toast.LENGTH_SHORT).show()
            }
        }
        val database = Room.databaseBuilder(context, ContactDatabase::class.java, "contacts").build()
        var List by remember { mutableStateOf(emptyList<Contact>()) }
        var scope = rememberCoroutineScope()
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Your Location : Latitude is ${currentLocation.longitude}\nLongitude is ${currentLocation.latitude}",modifier=Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Button(onClick = {
                if (permissions.all {
                        ContextCompat.checkSelfPermission(this@LocationActivity,it)==PackageManager.PERMISSION_GRANTED
                    }) {
                    Log.d("Hello",List.toString())
                    scope.launch(Dispatchers.IO) {
                        List = database.itemDao().getAllItems().toMutableList()
                        val smsManager: SmsManager = SmsManager.getDefault()
                        Log.d("Hello",List.toString())
                        for (item in List) {
                            smsManager.sendTextMessage(
                                item.phoneNumber,
                                null,
                                "Hello",
                                null,
                                null
                            )
                        }
                    }
                    startLocationUpdates()
                }
                else{
                    launcherMultiplePermissions.launch(permissions)
                }
            },modifier=Modifier.fillMaxWidth()) {
                Text(text = "Get Your Location", textAlign = TextAlign.Center)
            }
        }
    }
}