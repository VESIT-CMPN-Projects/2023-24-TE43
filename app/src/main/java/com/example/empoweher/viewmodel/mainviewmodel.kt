package com.example.empoweher.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.empoweher.model.DataState
import com.example.empoweher.model.event
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class mainviewmodel :ViewModel(){
    val response: MutableState<DataState> =mutableStateOf(DataState.Empty)
    init {
        fetch()
    }
    private fun fetch(){
        val events= mutableListOf<event>()
        response.value=DataState.Loading
        FirebaseDatabase.getInstance().getReference("Event").addListenerForSingleValueEvent(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){
                    val e=data.getValue(event::class.java)
                    if (e!=null) {
                        events.add(e)
                    }
                }
                response.value=DataState.Success(events)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value=DataState.Failure(error.message)
            }

        })

    }
}