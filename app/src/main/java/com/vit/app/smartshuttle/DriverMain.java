package com.vit.app.smartshuttle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.vit.app.smartshuttle.modals.LogObject;
import com.vit.app.smartshuttle.remote.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DriverMain extends AppCompatActivity {
    final int REQUEST_CODE=1234;
    // Time between location updates (5000 milliseconds or 5 seconds)
    final long MIN_TIME = 3000;
    // Distance between location updates (1000m or 1km)
    final float MIN_DISTANCE = 1000;
    Api api;

    String Location_provider = LocationManager.GPS_PROVIDER;

    LocationManager mLocationManager;
    LocationListener mLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);
getweatherforcurrentlocation();


    }

    // TODO: Add onResume() here:
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Clima", "On resume called");
        Intent myIntent = getIntent();



    }
    private void getweatherforcurrentlocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Clima", "OnLocChanged call back received");
                String longitude = String.valueOf(location.getLongitude());
                String latitude=String.valueOf(location.getLatitude());

                Log.d("Clima","The latitude is"+latitude);
                Log.d("Clima","The longitude  is"+longitude);
//                Call<LogObject> call = api.validate (regNo, pwd);
//                call.enqueue (new Callback<LogObject>() {
//                    @Override
//                    public void onResponse(Call<LogObject> call, Response<LogObject> response) {
//                        LogObject res=response.body ();
//                        Log.d("shuttt",res.getStatus());
//                        try{
//
//
//                            if(res.getStatus().equals ("1")){
////                        SharedPreferences.Editor editor = mSharedPreferences.edit();
////                        editor.putBoolean("status", false);
////                        if(admin.getType ().equals ("0"))editor.putBoolean ("Admin",false);
////
////                        else if(admin.getType ().equals ("1"))editor.putBoolean ("Admin",true);
////
////                        editor.apply();
//
//
//                                Log.d("ipactt","hi "+res+"  ");
//                                Intent intent = new Intent (LoginActivity.this,MainActivity.class);
//                                startActivity(intent);
//                                finish ();
//                                Toast.makeText (LoginActivity.this, "Hi You are an successffully", Toast.LENGTH_SHORT).show ();
//                            }}catch (NullPointerException e){
//                            Log.d("ipactt","Null Pointer");
//                        }
//
//                        // Log.d("ipactt",admin.getLogin_status ()+" "+admin.getName ());
//                    }
//
//                    @Override
//                    public void onFailure(Call<LogObject> call, Throwable t) {
//                        Log.d("ipactt",t.getMessage ());
//                        Toast.makeText (LoginActivity.this, t.getMessage (), Toast.LENGTH_SHORT).show ();
//
//                    }
//                });



            }



            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("Clima", "Location is diabled");

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION },REQUEST_CODE);

            return;
        }
        mLocationManager.requestLocationUpdates(Location_provider, MIN_TIME, MIN_DISTANCE, mLocationListener);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode== REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Log.d("Clima","OnRequest Pemission Result(): GRANTED!");
                getweatherforcurrentlocation();
            }else{
                Log.d("Clima","Rejeceted!");

            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(mLocationManager !=null) mLocationManager.removeUpdates(mLocationListener);
    }
}
