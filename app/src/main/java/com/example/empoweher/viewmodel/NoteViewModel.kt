package com.example.empoweher.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.empoweher.model.DataState
import com.example.empoweher.model.Note
import com.example.empoweher.model.Question
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class NoteViewModel(userId:String,mode:Int,date:Int) : ViewModel() {
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)
    val mode=mode
    val userId=userId
    var path=""
    val date=date
    init {
        fetch()
    }

    private fun fetch(){
        path = if (mode==1){
            "notes/notes/${userId}/hidden/"
        } else{
            "notes/notes/${userId}/visible/"
        }
        val notes= mutableListOf<Note>()
        response.value= DataState.Loading

        FirebaseDatabase.getInstance().getReference(path).addListenerForSingleValueEvent(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){
                    val e=data.getValue(Note::class.java)
                    if (e!=null) {

                        if (e.time!!.toInt()>=date &&e.time!!.toInt()<=date+86400000 ) {
                            notes.add(e)
                        }
                    }
                }
                Log.d("hello",notes.toString())
                response.value= DataState.SuccessNote(notes)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value= DataState.Failure(error.message)
            }

        })

    }
}