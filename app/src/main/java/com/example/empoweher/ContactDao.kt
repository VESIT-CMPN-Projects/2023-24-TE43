package com.example.empoweher

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert
import java.util.concurrent.Flow

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

}