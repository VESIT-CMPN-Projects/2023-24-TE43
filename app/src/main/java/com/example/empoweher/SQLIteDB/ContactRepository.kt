package com.example.empoweher.SQLIteDB

import kotlinx.coroutines.flow.Flow

interface ContactRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllContactStream(): List<Contact>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getContactStream(id: Int): Flow<Contact?>

    /**
     * Insert item in the data source
     */
    suspend fun insertContact(contact: Contact)

    /**
     * Delete item from the data source
     */
    suspend fun deleteContact(contact: Contact)

    /**
     * Update item in the data source
     */
    suspend fun updateContact(contact: Contact)
}