package com.foamy.guruku;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://guruku-ea4cb-default-rtdb.asia-southeast1.firebasedatabase.app");
    EditText EdtUsername, EdtPassword;
    public static boolean chkgu = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = this.getSharedPreferences(getString(R.string.UsernameG), Context.MODE_PRIVATE);
        EdtPassword = findViewById(R.id.Edtpassword);
        EdtUsername = findViewById(R.id.EdtUsername);
        checkguest();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String usr = pref.getString(getString(R.string.saved_guest), "");
        if (usr.length()>4) {
            this.finishAffinity();
        }
    }

    public void Guest(View v) {
        Intent intent = new Intent(this, LoginAsAGuest.class);
        startActivity(intent);
    }

    public void Reg(View v) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void itn() {
        Intent intent = new Intent(this, WelcomePage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void checkguest() {
        String usr = pref.getString(getString(R.string.saved_guest), "");
        if (usr.length()>4) {
            itn();
        }
    }
    public void LoginReg(View v){
        DatabaseReference myRef = database.getReference("User").child(EdtUsername.getText().toString());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && EdtUsername.getText().toString().trim().length()>0){
                    if(snapshot.hasChild(EdtPassword.getText().toString()) && EdtPassword.getText().toString().trim().length()>0)
                    {
                        pref.edit().putString(getString(R.string.saved_guest),myRef.getKey()).apply();
                        pref.edit().putString(getString(R.string.password),myRef.child(EdtPassword.getText().toString()).getKey()).apply();
                        pref.edit().putString(getString(R.string.pertambahan),
                                snapshot.child(EdtPassword.getText().toString()).child("Pertambahan").getValue(String.class)).apply();
                        pref.edit().putString(getString(R.string.pengurangan),
                                snapshot.child(EdtPassword.getText().toString()).child("Pengurangan").getValue(String.class)).apply();
                        pref.edit().putString(getString(R.string.perkalian),
                                snapshot.child(EdtPassword.getText().toString()).child("Perkalian").getValue(String.class)).apply();
                        pref.edit().putString(getString(R.string.pembagian),
                                snapshot.child(EdtPassword.getText().toString()).child("Pembagian").getValue(String.class)).apply();
                        chkgu = false;
                        itn();
                    }else{
                        EdtPassword.setError("Password Salah");
                    }

                }else{
                    EdtUsername.setError("Username Salah");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}