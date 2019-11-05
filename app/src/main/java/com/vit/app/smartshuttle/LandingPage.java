package com.vit.app.smartshuttle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vit.app.smartshuttle.modals.LogObject;
import com.vit.app.smartshuttle.remote.Api;

public class LandingPage extends AppCompatActivity {
    EditText id,pw;
    Button button;
    Api api;
    LogObject login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        findViewById(R.id.driver).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LandingPage.this,LoginDriver.class);
            startActivity(intent);
        }
    });

        findViewById(R.id.student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPage.this,LoginActivity.class);
                startActivity(intent);
            }
        });
}
}
