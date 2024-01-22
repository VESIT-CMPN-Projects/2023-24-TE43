package com.example.empoweher.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import androidx.test.espresso.Espresso
import com.example.empoweher.R
import com.example.empoweher.SQLIteDB.Contact
import com.example.empoweher.SQLIteDB.ContactDatabase
import kotlinx.coroutines.launch


class ContactActivity : AppCompatActivity() {
    private lateinit var contactNumber:String
    private lateinit var contactName:String
    private lateinit var contact: Contact
    private lateinit var contacts:ArrayList<String>
    private var numberList:ArrayList<String> = ArrayList()
    private var nameList:ArrayList<String> = ArrayList()
    private val REQUEST_CODE_PICK_CONTACT =57

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_contact)
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS),0)
        }else {
            MultiplePhonePicker()
        }
    }
    fun MultiplePhonePicker(){
        val intent = Intent("intent.action.INTERACTION_TOPMENU")
        intent.putExtra("additional", "phone-multi")
        startActivityForResult(intent, REQUEST_CODE_PICK_CONTACT)
    }
    @SuppressLint("Range")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)
        when (reqCode) {
            REQUEST_CODE_PICK_CONTACT -> when (resultCode) {
                RESULT_OK -> {
                    val bundle:Bundle? = data?.extras
                    contacts = bundle!!.getStringArrayList("result")!!
                }
            }
        }
        for (contact in contacts){
           val splitted=contact.split(';')
            nameList.add(splitted[3])
            numberList.add(splitted[1])
        }
        setContent {
            val scope = rememberCoroutineScope()
            LaunchedEffect(key1 = Unit) {
                scope.launch {
                    val db= Room.databaseBuilder(this@ContactActivity, ContactDatabase::class.java,"contacts").build()
                    for (i in 0 until nameList.count()) {
                        val fullName=nameList[i].split(' ')
                        val fName=fullName[0]
                        val lName=fullName[1]
                        contact = Contact(0, fName, lName, numberList[i], true)
                        db.itemDao().insertContact(contact = contact)
                    }
                    Espresso.pressBack()
                }
            }
        }
    }
}