package com.vit.app.smartshuttle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;
import com.vit.app.smartshuttle.modals.Profile;
import com.vit.app.smartshuttle.remote.APIUtils;
import com.vit.app.smartshuttle.remote.Api;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recharge extends AppCompatActivity implements PaymentResultWithDataListener {

    EditText money;
    Api api;
    String r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        api= APIUtils.getUserService();
        money = findViewById(R.id.paymentMoney);

        findViewById(R.id.paymentButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!money.getText().toString().equals("")){
                    r=money.getText().toString();
                    int cash= Integer.parseInt(money.getText().toString());
                    startpayment(cash);
                }
                else{
                    Toast.makeText(Recharge.this, "Fill recharge amount!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startpayment(int cash) {
        Checkout checkout = new Checkout();

        checkout.setImage(R.drawable.ic_launcher_foreground);
        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();


            options.put("name", "Smart Shuttle");


            options.put("description", "Reference No. #123456");
            options.put("currency", "INR");


            options.put("amount",cash*100);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("Shuttt", "Error in starting Razorpay Checkout", e);
        }
    }





    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Toast.makeText(this, "Payment Successfull for " , Toast.LENGTH_SHORT).show();

        Call<Object> call = api.transaction ("16BCI0117",paymentData.getPaymentId(), r,"1");
        call.enqueue (new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                try{

                    Intent intent = new Intent(Recharge.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch(NullPointerException e){
                    Log.d("shuttt","Null Pointer");
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("shuttt",t.getMessage ());
                Toast.makeText (Recharge.this, t.getMessage (), Toast.LENGTH_SHORT).show ();

            }
        });

    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(this, "Payment Error " + s, Toast.LENGTH_SHORT).show();
        Log.d("Shuttt",s);
    }
}
