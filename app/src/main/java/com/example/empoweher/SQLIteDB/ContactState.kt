package com.example.empoweher.SQLIteDB

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",
    val emergency: Boolean = false
)