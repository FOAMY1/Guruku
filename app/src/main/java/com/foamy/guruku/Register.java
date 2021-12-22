package com.foamy.guruku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    EditText FirstName, LastName, UserReg, Passreg;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://guruku-ea4cb-default-rtdb.asia-southeast1.firebasedatabase.app");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirstName = findViewById(R.id.EdtFirstName);
        LastName = findViewById(R.id.EdtLastName);
        UserReg = findViewById(R.id.EdtUsernameReg);
        Passreg = findViewById(R.id.EdtPasswordReg);
        UserReg.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                if (UserReg.getText().toString().trim().length()<4){
                    UserReg.setError("Username harus lebih dari 3 huruf");
                }
                else{
                    UserReg.setError(null);
                }
            }
            else{
                if (UserReg.getText().toString().trim().length()<4){
                    UserReg.setError("Username harus lebih dari 3 huruf");
                }
                else{
                    UserReg.setError(null);
                }
            }
        });
        Passreg.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                if(Passreg.getText().toString().trim().length()<6){
                    Passreg.setError("Password harus lebih dari 5 huruf");
                }
                else{
                    Passreg.setError(null);
                }
            }
            else{
                if(Passreg.getText().toString().trim().length()<6){
                    Passreg.setError("Password harus lebih dari 5 huruf");
                }
                else{
                    Passreg.setError(null);
                }
            }
        });
        FirstName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                if(FirstName.getText().toString().trim().length()<1){
                    FirstName.setError("First Name harus diisi");
                }
                else{
                    FirstName.setError(null);
                }
            }
            else{
                if(FirstName.getText().toString().trim().length()<1){
                    FirstName.setError("First Name harus diisi");
                }
                else{
                    FirstName.setError(null);
                }
            }
        });
        LastName.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus){
                if(LastName.getText().toString().trim().length()<1){
                    LastName.setError("First Name harus diisi");
                }
                else{
                    LastName.setError(null);
                }
            }
            else{
                if(LastName.getText().toString().trim().length()<1){
                    LastName.setError("First Name harus diisi");
                }
                else{
                    LastName.setError(null);
                }
            }
        });
    }
    public void Registerme(View v){
        if(UserReg.getError()!= null || Passreg.getError()!=null || LastName.getError()!=null || FirstName.getError()!=null
                || UserReg.getText().toString().trim().length()<4 || Passreg.getText().toString().trim().length()<6
                || LastName.getText().toString().trim().length()<1 || FirstName.getText().toString().trim().length()<1){

            Toast.makeText(this, "Data masih belum lengkap", Toast.LENGTH_SHORT).show();
        }
        else{
            DatabaseReference myRef = database.getReference("User").child(UserReg.getText().toString());
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        UserReg.setError("Username taken");
                    }
                    else{
                        DatabaseReference myRef3 = myRef.child(Passreg.getText().toString());
                        myRef3.child("FirstName").setValue(FirstName.getText().toString());
                        myRef3.child("LastName").setValue(LastName.getText().toString());
                        myRef3.child("Pertambahan").setValue("0");
                        myRef3.child("Pengurangan").setValue("0");
                        myRef3.child("Perkalian").setValue("0");
                        myRef3.child("Pembagian").setValue("0");
                        finish();
                        onBackPressed();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}