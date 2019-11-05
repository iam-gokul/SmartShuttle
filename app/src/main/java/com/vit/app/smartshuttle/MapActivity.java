package com.vit.app.smartshuttle;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vit.app.smartshuttle.modals.Cabs;
import com.vit.app.smartshuttle.modals.LogObject;
import com.vit.app.smartshuttle.remote.APIUtils;
import com.vit.app.smartshuttle.remote.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    Api api;
    private TextView textView;
    private GoogleMap mMap;
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        textView=findViewById(R.id.cabcount);
        api= APIUtils.getUserService();

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(12.971105,79.159272);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15), 200, null);
      mMap.addCircle(new CircleOptions()
                .center(new LatLng(12.971168, 79.158886))
                .radius(10000)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Call<Cabs[]> call = api.getLocations();
                call.enqueue (new Callback<Cabs[]>() {
                    @Override
                    public void onResponse(Call<Cabs[]> call, Response<Cabs[]> response) {
                        Cabs[] res=response.body ();
                        Log.d("Shuttt","size is "+res.length);
                        textView.setText(res.length+"");
                        try{
                            for(int i=0;i<res.length;i++){
                                Double lat=Double.parseDouble(res[i].getLat());
                                Double lng=Double.parseDouble(res[i].getLon());
                                Log.d("shuttt",lat+" "+lng);

                                latlngs.add(new LatLng(lat, lng));


                            }
                            for (LatLng point : latlngs) {
                                options.position(point);
                                options.title("Cabs");
                                mMap.addMarker(options);
                            }
                        }catch (NullPointerException e){
                            Log.d("shuttt","Null Pointer");
                        }

                        // Log.d("ipactt",admin.getLogin_status ()+" "+admin.getName ());
                    }

                    @Override
                    public void onFailure(Call<Cabs[]> call, Throwable t) {
                        Log.d("shuttt",t.getMessage ());

                    }
                });
                mMap.clear();

                handler.postDelayed(this, 1000);
            }
        }, 1000);






    }
}