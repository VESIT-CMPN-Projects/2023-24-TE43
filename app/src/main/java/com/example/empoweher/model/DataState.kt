package com.example.empoweher.model

sealed class DataState {
    class  Success(val data:MutableList<event>):DataState()
    class  Failure(val message:String):DataState()
    object Loading:DataState()
    object Empty:DataState()
}