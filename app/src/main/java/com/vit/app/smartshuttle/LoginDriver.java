package com.vit.app.smartshuttle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vit.app.smartshuttle.modals.LogObject;
import com.vit.app.smartshuttle.remote.APIUtils;
import com.vit.app.smartshuttle.remote.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginDriver extends AppCompatActivity {

    Api api;
    EditText id,pw;
    Button button;
    LogObject login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_driver);


        id=findViewById(R.id.loginid);
        pw=findViewById(R.id.loginpw);
        api = APIUtils.getUserService ();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mid=id.getText().toString().toUpperCase();
                String mpw= pw.getText().toString();
                if(!mid.equals("") && !mpw.equals("")){
                    validate(mid,mpw);
                }
                else{
                    Toast.makeText(LoginDriver.this, "Please fill id and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void validate(String regNo, String pwd) {
        Log.d ("ipactt", regNo);
        Call<LogObject> call = api.driverValidate (regNo, pwd);
        call.enqueue (new Callback<LogObject>() {
            @Override
            public void onResponse(Call<LogObject> call, Response<LogObject> response) {
                LogObject res=response.body ();
                Log.d("shuttt",res.getStatus());
                try{


                    if(res.getStatus().equals ("1")){
                        Log.d("ipactt","hi "+res+"  ");
                        Intent intent = new Intent (LoginDriver.this,DriverMain.class);
                        startActivity(intent);
                        finish ();
                        Toast.makeText (LoginDriver.this, "Hi You are successffully logged in", Toast.LENGTH_SHORT).show ();
                    }}
                catch (NullPointerException e){
                    Log.d("ipactt","Null Pointer");
                }

                // Log.d("ipactt",admin.getLogin_status ()+" "+admin.getName ());
            }

            @Override
            public void onFailure(Call<LogObject> call, Throwable t) {
                Log.d("ipactt",t.getMessage ());
                Toast.makeText (LoginDriver.this, t.getMessage (), Toast.LENGTH_SHORT).show ();

            }
        });

    }
}
