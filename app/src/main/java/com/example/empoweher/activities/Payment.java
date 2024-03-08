package com.example.empoweher.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.empoweher.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class Payment extends AppCompatActivity implements PaymentResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.payButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startPayment();
            }
        });

    }
    public void startPayment() {

        Checkout checkout = new Checkout();

        checkout.setImage(R.mipmap.ic_launcher);

        final Activity activity = this;


        try {
            JSONObject options = new JSONObject();

            options.put("name", "EmpowerHer");
            options.put("description", "Payment For An Event");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg");
            options.put("send_sms_hash", true);
            options.put("allow rotation", false);


            options.put("currency", "INR");
            options.put("amount", "100");

            JSONObject preFill=new JSONObject();
            preFill.put("prefill.email", "email");
            preFill.put("prefill.contact","phone");

            options.put("prefill",preFill);

            checkout.open(activity, options);

        } catch(Exception e) {
            Toast.makeText(activity,"Error in Payment: "+ e.getMessage(),Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"Payment Success"+s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"Payment Error"+s,Toast.LENGTH_SHORT).show();
    }
}