package com.example.empoweher.model

import android.accounts.AuthenticatorDescription

data class event (
    var eventId : String?=null,
    var eventName : String?=null,
    var address:String?=null,
    var description: String?=null,
    var capacity:Int=999,
    var startDate:String?=null,
    var endDate:String?=null,
    var hostId:String?=null,
    var hostName: String?=null
)

