package com.example.empoweher.SQLIteDB

sealed interface ContactEvent{
    object SaveContact: ContactEvent
    data class SetFirstName(val firstName: String): ContactEvent
    data class SetLastName(val lastName: String): ContactEvent
    data class SetPhoneNumber(val phoneNumber: String): ContactEvent
    data class setEmergency(val emergency: Boolean): ContactEvent
    object showDialog: ContactEvent // Check this if needed or else remove before running
    object hideDialog: ContactEvent // Check this if needed or else remove before running
    data class DeleteContact(val contact: Contact): ContactEvent
}