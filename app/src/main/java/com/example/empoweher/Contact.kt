package com.example.empoweher

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigInteger

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val emergency: Boolean,
    val temp1: String,

)
