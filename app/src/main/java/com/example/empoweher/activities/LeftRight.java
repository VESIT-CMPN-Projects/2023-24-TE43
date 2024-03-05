package com.example.empoweher.activities;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.empoweher.R;

public class LeftRight extends AppCompatActivity implements SensorEventListener {

    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_left_right);
        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager!=null){
            Sensor accleroSensor= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if (accleroSensor!=null){

                sensorManager.registerListener(this, accleroSensor,SensorManager.SENSOR_DELAY_NORMAL);


            }

        }
        else{

        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            txt= findViewById(R.id.txtValuesLeftRight);
            txt.setText("X: "+event.values[0] + ",\nY: "+event.values[1]+",\nZ: "+event.values[2]);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}