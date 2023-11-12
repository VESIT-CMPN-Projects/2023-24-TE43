package com.example.empoweher

sealed interface ContactEvent{
    object SaveContact: ContactEvent
    data class SetFirstName(val firstName: String): ContactEvent
    data class SetLastName(val lastName: String): ContactEvent
    data class SetPhoneNumber(val phoneNumber: String): ContactEvent
    object showDialog: ContactEvent
    object hideDialog: ContactEvent
}