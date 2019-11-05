package com.vit.app.smartshuttle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
public class LoginActivity extends AppCompatActivity {
    EditText id,pw;
    Button button;
    Api api;
    LogObject login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                    Toast.makeText(LoginActivity.this, "Please fill id and password", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void validate(String regNo, String pwd) {
        Log.d ("ipactt", regNo);
        Call<LogObject> call = api.validate (regNo, pwd);
        call.enqueue (new Callback<LogObject>() {
            @Override
            public void onResponse(Call<LogObject> call, Response<LogObject> response) {
                LogObject res=response.body ();
                Log.d("shuttt",res.getStatus());
                try{


                    if(res.getStatus().equals ("1")){
//                        SharedPreferences.Editor editor = mSharedPreferences.edit();
//                        editor.putBoolean("status", false);
//                        if(admin.getType ().equals ("0"))editor.putBoolean ("Admin",false);
//
//                        else if(admin.getType ().equals ("1"))editor.putBoolean ("Admin",true);
//
//                        editor.apply();


                        Log.d("ipactt","hi "+res+"  ");
                        Intent intent = new Intent (LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish ();
                        Toast.makeText (LoginActivity.this, "Hi You are an successffully", Toast.LENGTH_SHORT).show ();
                    }}catch (NullPointerException e){
                    Log.d("ipactt","Null Pointer");
                }

               // Log.d("ipactt",admin.getLogin_status ()+" "+admin.getName ());
            }

            @Override
            public void onFailure(Call<LogObject> call, Throwable t) {
                Log.d("ipactt",t.getMessage ());
                Toast.makeText (LoginActivity.this, t.getMessage (), Toast.LENGTH_SHORT).show ();

            }
        });

    }
}
