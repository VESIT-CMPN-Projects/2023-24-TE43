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
    object Map: Screen(route = "map")
    object EventForm: Screen(route = "eventForm")
    object ContactsList: Screen(route = "contactsList")
    object UpdateContactList: Screen(route = "updateContactsList")
    object EventCard: Screen(route = "eventCard")
    object DetailedEventCard: Screen(route = "detailedEventCard")
    object AddContact: Screen(route = "addContact")
    object Temp1: Screen(route = "temp1")
    object ContactOption: Screen(route = "contactOptions")
    object Alerts: Screen(route = "alerts")
    object Details: Screen(route = "details")
    object DetailsDesignation: Screen(route = "detailsDesignation")
    object DetailsInterests: Screen(route = "detailsInterests")
    object DetailsDp: Screen(route = "detailsDp")
    object Answer: Screen(route = "Answer")
    object Profile: Screen(route = "profile")
    object Registration:Screen(route = "registration")
    object Onboarding:Screen(route = "onboarding")
    object AskQuestion:Screen(route = "askQuestion")
    object GiveAnswer:Screen(route = "giveAnswer")
}