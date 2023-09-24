package com.example.empoweher.model

sealed class Screen(val route:String)
{
    object Login: Screen(route = "sign_in")
    object Home: Screen(route= "home")
    object Safety: Screen(route = "safety")
    object FakeCall: Screen(route = "fakecall")
    object Ask: Screen(route = "ask")
    object Events: Screen(route = "events")
    object Settings: Screen(route = "settings")
    object EmergencyList: Screen(route = "emergencyList")
}
