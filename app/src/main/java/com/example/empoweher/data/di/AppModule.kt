package com.example.empoweher.data.di

import android.app.Application
import com.example.empoweher.data.DefaultLocationTracker
import com.example.empoweher.domain.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object AppModule {
    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule {

        @Provides
        @Singleton
        fun providesFusedLocationProviderClient(
            application: Application
        ): FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(application)

        @Provides
        @Singleton
        fun providesLocationTracker(
            fusedLocationProviderClient: FusedLocationProviderClient,
            application: Application
        ): LocationTracker = DefaultLocationTracker(
            fusedLocationProviderClient = fusedLocationProviderClient,
            application = application
        )
    }
}