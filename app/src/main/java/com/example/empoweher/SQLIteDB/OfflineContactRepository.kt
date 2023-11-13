package com.example.empoweher.SQLIteDB

import kotlinx.coroutines.flow.Flow

class OfflineContactRepository(private val contactDao: ContactDao) : ContactRepository {
    override fun getAllContactStream(): List<Contact> = contactDao.getAllItems()

    override fun getContactStream(id: Int): Flow<Contact?> = contactDao.getContact(String.toString())

    override suspend fun insertContact(contact: Contact) = contactDao.insertContact(contact)

    override suspend fun deleteContact(contact: Contact) = contactDao.deleteContact(contact)

    override suspend fun updateContact(contact: Contact) = contactDao.updateContact(contact)

}