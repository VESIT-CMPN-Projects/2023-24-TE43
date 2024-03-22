package com.example.empoweher.activities

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BluetoothReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action=intent?.action

        if (action==BluetoothAdapter.ACTION_STATE_CHANGED){
            when(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,BluetoothAdapter.ERROR)){
                BluetoothAdapter.STATE_ON->{
                    Log.d("JWSH","State On")

                }
                BluetoothAdapter.STATE_OFF->{
                    Log.d("JWSH","State Off")

                }
                BluetoothAdapter.STATE_TURNING_ON->{
                    Log.d("JWSH","Turning On")

                }
                BluetoothAdapter.STATE_TURNING_OFF->{
                    Log.d("JWSH","Turning Off")

                }
            }
        }
    }
}