package com.example.empoweher.SQLIteDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigInteger

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val emergency: Boolean,
    val temp1: String = "temp1",
    val temp2: String = "temp2",
    val temp3: Int = 3,
    val temp4: Int = 4
)