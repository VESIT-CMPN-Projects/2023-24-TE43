package com.example.empoweher.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.empoweher.R
import com.example.empoweher.SQLIteDB.Contact
import com.example.empoweher.SQLIteDB.ContactDatabase
import kotlinx.coroutines.launch


class ContactActivity : AppCompatActivity() {
    private lateinit var contactNumber:String
    private lateinit var contactName:String
    private lateinit var contact: Contact
    private val PICK_CONTACT =1

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_contact)
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS),0)
        }else {
            val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            startActivityForResult(intent, PICK_CONTACT)
        }
    }

    @SuppressLint("Range")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(reqCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(reqCode, resultCode, data)
        when (reqCode) {
            PICK_CONTACT -> if (resultCode == Activity.RESULT_OK) {
                val contactData = data?.data
                val c = managedQuery(contactData, null, null, null, null)
                if (c.moveToFirst()) {
                    val id = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                    val hasPhone =
                        c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                    if (hasPhone.equals("1", ignoreCase = true)) {
                        val phones = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                            null, null
                        )
                        phones!!.moveToFirst()
                        contactNumber = phones!!.getString(phones!!.getColumnIndex("data1"))
                        contactName = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        val contactList=contactName.split(' ')
                        val fName=contactList[0]
                        val lName=contactList[1]
                        contact = Contact(0, fName, lName, contactNumber, true)
                        Log.d("Cute Prasad","Name : "+contactName+" Number : "+contactNumber)
                        setContent {
                            val scope = rememberCoroutineScope()
                            LaunchedEffect(key1 = Unit) {
                                scope.launch {
                                    val db= Room.databaseBuilder(this@ContactActivity, ContactDatabase::class.java,"contacts").build()
                                    db.itemDao().insertContact(contact = contact)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}