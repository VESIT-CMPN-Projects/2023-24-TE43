package com.example.empoweher.SQLIteDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import com.example.empoweher.SQLIteDB.Contact

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

}