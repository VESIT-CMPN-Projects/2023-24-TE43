package com.example.empoweher.model

sealed class DataState {
    class  Success(val data:MutableList<Event>):DataState()
    class  SuccessQuestion(val data:MutableList<Question>):DataState()
    class  SuccessAnswer(val data:MutableList<Answer>):DataState()
    class  SuccessBooked(val data:MutableList<String>):DataState()
    class  Failure(val message:String):DataState()
    object Loading:DataState()
    object Empty:DataState()
}