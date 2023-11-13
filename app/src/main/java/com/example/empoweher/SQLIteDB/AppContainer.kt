package com.example.empoweher.SQLIteDB

import android.content.Context

interface AppContainer {
    val contactRepository: ContactRepository
}

class AppDataContainer(private val context: Context) : AppContainer {

    override val contactRepository: ContactRepository by lazy {
        OfflineContactRepository(ContactDatabase.getDatabase(context).itemDao())
    }
}