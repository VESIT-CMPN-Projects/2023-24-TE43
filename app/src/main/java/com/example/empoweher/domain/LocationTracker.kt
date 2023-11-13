package com.example.empoweher.domain

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}