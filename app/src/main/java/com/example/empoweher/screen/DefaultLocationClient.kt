package com.example.empoweher.screen

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
//import com.google.android.gms.location.LocationRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class DefaultLocationClient (
    private val context : Context,
    private val client: FusedLocationProviderClient
) : LocationClient {

    override fun getLocationUpdates(interval: Long): Flow<Location> {
        return callbackFlow {
            if(!context.hasLocationPermission()){
                throw LocationClient.LocationException("Missing Location Permission")
            }

            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            if(!isGpsEnabled && !isNetworkEnabled){
                throw LocationClient.LocationException("GPS is Disabled")
            }

//            val request = LocationRequest.create()


            val locationallback = object : LocationCallback(){
                override fun onLocationResult(p0: LocationResult){
                    super.onLocationResult(p0)
                }
            }
        }
    }
}