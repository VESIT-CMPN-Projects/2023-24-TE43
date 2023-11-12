package com.example.empoweher.SQLIteDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.empoweher.SQLIteDB.Contact
import com.example.empoweher.SQLIteDB.ContactDao

@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase: RoomDatabase() {
    abstract val dao: ContactDao
}