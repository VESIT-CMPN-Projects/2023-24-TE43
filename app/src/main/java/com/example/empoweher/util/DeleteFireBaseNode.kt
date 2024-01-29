package com.example.empoweher.util

import androidx.test.espresso.Espresso
import com.google.firebase.database.FirebaseDatabase

fun DeleteFireBaseNode(nodePath:String?) {
    val dbref = FirebaseDatabase.getInstance().getReference();
    val node=dbref.child(nodePath!!)
    node.removeValue().addOnSuccessListener {
        Espresso.pressBack()
    }

}