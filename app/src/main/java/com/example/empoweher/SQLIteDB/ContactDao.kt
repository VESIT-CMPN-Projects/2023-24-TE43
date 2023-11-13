package com.example.empoweher.SQLIteDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.empoweher.SQLIteDB.Contact

import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert
    suspend fun insertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Query("SELECT * from contact WHERE firstName = :firstName")
    fun getContact(firstName: String): Flow<Contact>

    @Query("SELECT * from contact ORDER BY firstName ASC")
    fun getAllItems(): Flow<List<Contact>>
}