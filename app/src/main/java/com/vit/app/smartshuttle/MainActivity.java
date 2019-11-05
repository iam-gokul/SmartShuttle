package com.vit.app.smartshuttle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.vit.app.smartshuttle.modals.LogObject;
import com.vit.app.smartshuttle.modals.Profile;
import com.vit.app.smartshuttle.remote.APIUtils;
import com.vit.app.smartshuttle.remote.Api;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Api api;
    TextView username,balance,trips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity activity = this;
        api= APIUtils.getUserService();
        final String s = "16BCI0117";
        username = findViewById(R.id.nametextview);
        balance  = findViewById(R.id.balancetextview);
        trips = findViewById(R.id.tripstextview);
        Call<Profile> call = api.loginp (s);
        call.enqueue (new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                Profile res=response.body ();
                try{
                    username.setText(res.getUserName());
                    balance.setText("My Balance ₹ "+res.getBalance());
                    int t = Integer.parseInt(res.getBalance())/15;
                    trips.setText("Remaining trips "+ t );
                    Log.d("shutt","hi "+res.getUserName());

                    }
                    catch(NullPointerException e){
                    Log.d("shuttt","Null Pointer");
                }

                // Log.d("ipactt",admin.getLogin_status ()+" "+admin.getName ());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d("shuttt",t.getMessage ());
                Toast.makeText (MainActivity.this, t.getMessage (), Toast.LENGTH_SHORT).show ();

            }
        });

        findViewById(R.id.recharge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Recharge.class);
                startActivity(intent);


            }
        });
        findViewById(R.id.mapbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);

            }
        });
    }



}
