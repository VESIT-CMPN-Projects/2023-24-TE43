package com.example.empoweher.model

sealed class Screen(val route:String)
{
    object Login: Screen(route = "sign_in")
    object Home: Screen(route= "home")
    object Safety: Screen(route = "safety")
    object FakeCall: Screen(route = "fakecall")
    object Ask: Screen(route = "temp1")
    object Events: Screen(route = "temp2")
    object Settings: Screen(route = "settings")
    object EmergencyList: Screen(route = "emergencyList")
    object Map: Screen(route = "map")
    object Temp1: Screen(route = "temp1")
    object Temp2: Screen(route = "temp2")
    object EventForm: Screen(route = "eventForm")
    object ContactsList: Screen(route = "contactsList")
    object UpdateContactList: Screen(route = "updateContactsList")

    object EventCard: Screen(route = "eventCard")
    object DetailedEventCard: Screen(route = "detailedEventCard")
}
