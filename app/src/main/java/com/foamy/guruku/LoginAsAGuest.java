package com.foamy.guruku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginAsAGuest extends AppCompatActivity {
    EditText EdtGuestuser;
    SharedPreferences pref;
    String saveduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as_aguest);
        EdtGuestuser = findViewById(R.id.EdtGuestUser);
        pref = this.getSharedPreferences(getString(R.string.UsernameG), Context.MODE_PRIVATE);
        checkguest();
        EdtGuestuser.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (EdtGuestuser.getText().toString().trim().length() < 4) {
                    EdtGuestuser.setError("Username harus lebih dari 3 huruf");
                } else {
                    EdtGuestuser.setError(null);
                }
            }
        });
    }
    public void itn(){
        Intent intent = new Intent(this, WelcomePage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    public void checkguest(){
        String usr = pref.getString(getString(R.string.saved_guest),"");
        if (usr.length()>4){
            itn();
        }
    }
    public void GuestLog(View v){
        if (EdtGuestuser.getError()!=null || EdtGuestuser.getText().toString().length()<4){
            EdtGuestuser.setError("Username harus lebih dari 3 huruf");
        }
        else{
            saveduser = EdtGuestuser.getText().toString();
            pref.edit().putString(getString(R.string.saved_guest), saveduser).apply();
            MainActivity.chkgu = true;
            itn();
        }
    }
}