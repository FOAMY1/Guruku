package com.foamy.guruku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowScore extends AppCompatActivity {
    TextView showscore, showlastscore;
    String strscore, code, nama, password;
    SharedPreferences pref;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://guruku-ea4cb-default-rtdb.asia-southeast1.firebasedatabase.app");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);
        showscore = findViewById(R.id.TxtScore);
        showlastscore = findViewById(R.id.Txtlastscore);
        pref = this.getSharedPreferences(getString(R.string.UsernameG), Context.MODE_PRIVATE);
        GetIncomingIntent();
        nama = pref.getString(getString(R.string.saved_guest),"");
        password = pref.getString(getString(R.string.password),"");
        showlastscore.setText("Last Score : "+SoalPage.lastscore);
    }
    private void GetIncomingIntent(){
        if (getIntent().hasExtra("kode")){
            code = getIntent().getStringExtra("kode");
        }
    }
    public void showWhichScore(View view){
        if(code.equalsIgnoreCase("Pertambahan")){
            if(MainActivity.chkgu) {
                strscore = pref.getString(getString(R.string.pertambahan), "0");
                showscore.setText("Score : "+strscore);
            }else{
                strscore = pref.getString(getString(R.string.pertambahan), "0");
                DatabaseReference myref = database.getReference("User").child(nama).child(password);
                myref.child("Pertambahan").setValue(strscore);
                showscore.setText("Score : "+strscore);
            }
        }
        else if(code.equalsIgnoreCase("Pengurangan")){
            if(MainActivity.chkgu) {
                strscore = pref.getString(getString(R.string.pengurangan), "0");
                showscore.setText("Score : "+strscore);
            }else{
                strscore = pref.getString(getString(R.string.pengurangan), "0");
                DatabaseReference myref = database.getReference("User").child(nama).child(password);
                myref.child("Pengurangan").setValue(strscore);
                showscore.setText("Score : "+strscore);
            }
        }
        else if(code.equalsIgnoreCase("Perkalian")){
            if(MainActivity.chkgu) {
                strscore = pref.getString(getString(R.string.perkalian), "0");
                showscore.setText("Score : "+strscore);
            }else{
                strscore = pref.getString(getString(R.string.perkalian), "0");
                DatabaseReference myref = database.getReference("User").child(nama).child(password);
                myref.child("Perkalian").setValue(strscore);
                showscore.setText("Score : "+strscore);
            }
        }
        else if(code.equalsIgnoreCase("Pembagian")){
            if(MainActivity.chkgu) {
                strscore = pref.getString(getString(R.string.pembagian), "0");
                showscore.setText("Score : "+strscore);
            }else{
                strscore = pref.getString(getString(R.string.pembagian), "0");
                DatabaseReference myref = database.getReference("User").child(nama).child(password);
                myref.child("Pembagian").setValue(strscore);
                showscore.setText("Score : "+strscore);
            }
        }
    }
}