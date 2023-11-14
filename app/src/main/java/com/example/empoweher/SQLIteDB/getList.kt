package com.example.empoweher.SQLIteDB

import android.content.Context
import android.util.Log
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

suspend fun getList(database: ContactDatabase): Deferred<MutableList<Contact>> {
    var scope = CoroutineScope(Dispatchers.IO)
    var list = scope.async {
        database.itemDao().getAllItems().toMutableList()
    }
    Log.d("Hellllo1",list.await().toString())
    return list
}