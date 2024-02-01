package com.example.empoweher.activities

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.empoweher.R
import com.example.empoweher.model.LocationDetails
import com.example.empoweher.screen.safety.FakeCall
import com.example.empoweher.screen.safety.Safety
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class SafetyActivity: AppCompatActivity() {
    private var locationRequired: Boolean = false
    private val permissions = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.SEND_SMS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safety)
        setContent{
            var navController= rememberNavController()
            Safety(navigateToNextScreen = {route->
                navController.navigate(route)
            })
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            event?.startTracking();
            setContent{
                val location= LocationActivity()
                var currentLocation by remember {
                    mutableStateOf(LocationDetails(0.toDouble(),0.toDouble()))
                }
                val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        super.onLocationResult(locationResult)
                        for (location in locationResult.locations) {
                            currentLocation = LocationDetails(location.latitude, location.longitude)
                        }
                    }
                }
                val launcherMultiplePermissions = rememberLauncherForActivityResult(
                    ActivityResultContracts.RequestMultiplePermissions()
                ) { permissionMaps ->
                    val areGranted = permissionMaps.values.reduce { acc, next -> acc && next }
                    if (areGranted) {
                        locationRequired = true
                        location.startLocationUpdates(locationCallback,fusedLocationProviderClient,this@SafetyActivity)
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                    }
                }
                if(permissions.all{
                    ContextCompat.checkSelfPermission(
                        this@SafetyActivity,
                        it
                    ) == PackageManager.PERMISSION_GRANTED
            }) {
                    location.startLocationUpdates(locationCallback,fusedLocationProviderClient,this@SafetyActivity)
                    location.LocationSharing(
                        currentLocation,
                        this@SafetyActivity,
                        locationCallback,
                        fusedLocationProviderClient
                    )
                    Toast.makeText(
                        LocalContext.current,
                        currentLocation.latitude.toString() + "Longitude : " + currentLocation.longitude.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    FakeCall()
                }
                else{
                    launcherMultiplePermissions.launch(permissions)
                }
            }
        }
        return super.onKeyUp(keyCode, event)
    }
}