package com.example.empoweher.screen

import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun CheckPermission():Boolean{
    //this function will return a boolean
    //true: if we have permission
    //false if not
    if(
        ActivityCompat.checkSelfPermission(LocalContext.current,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
        ActivityCompat.checkSelfPermission(LocalContext.current,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    ){
        return true
    }

    return false

}