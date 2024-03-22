package com.example.empoweher.activities

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.empoweher.R
import com.example.empoweher.databinding.ActivityBluetoothBinding

class Bluetooth : AppCompatActivity() {
    lateinit var binding :ActivityBluetoothBinding
    lateinit var bluetoothAdapter: BluetoothAdapter
    lateinit var bluetoothManager: BluetoothManager
    lateinit var receiver:BluetoothReceiver
    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
            isGranted->
        if(isGranted) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBluetoothBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bluetoothManager=getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter=bluetoothManager.adapter

        enableDisableBT()
        receiver=BluetoothReceiver()
    }

    private fun enableDisableBT() {

            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.BLUETOOTH_CONNECT
                )==PackageManager.PERMISSION_GRANTED){
            }
            else if (shouldShowRequestPermissionRationale(android.Manifest.permission.BLUETOOTH_CONNECT)){
                Toast.makeText(this,"Permission", Toast.LENGTH_SHORT).show()
            }
            else{
                requestPermissionLauncher.launch(android.Manifest.permission.BLUETOOTH_CONNECT)
            }

        binding.btnBluetooth.setOnClickListener {
            if (!bluetoothAdapter.isEnabled){
                bluetoothAdapter.enable()
                val intent= Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivity(intent)

                val intentFilter=IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
                registerReceiver(receiver,intentFilter)

            }
            if(bluetoothAdapter.isEnabled){
                bluetoothAdapter.disable()

                val intentFilter=IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
                registerReceiver(receiver,intentFilter)

            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}