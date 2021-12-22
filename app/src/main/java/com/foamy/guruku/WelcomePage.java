package com.foamy.guruku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class WelcomePage extends AppCompatActivity {
    RecyclerView Materi;
    ArrayList<Materi> IsiMateri;
    ArrayList<Soal> IsiSoalTambah;
    ArrayList<Soal> IsiSoalMinus;
    ArrayList<Soal> IsiSoalKali;
    ArrayList<Soal> IsiSoalBagi;
    SharedPreferences pref;
    String nama;
    TextView txtwelcome;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://guruku-ea4cb-default-rtdb.asia-southeast1.firebasedatabase.app");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        Materi = findViewById(R.id.RccMateri);
        txtwelcome = findViewById(R.id.TxtUserMain);
        InitData();
        Materi.setAdapter(new MateriAdapter(IsiMateri));
        int spancount = 2;
        Materi.setLayoutManager(new GridLayoutManager(this,spancount,GridLayoutManager.VERTICAL,false));
        pref = this.getSharedPreferences(getString(R.string.UsernameG), Context.MODE_PRIVATE);
        nama = pref.getString(getString(R.string.saved_guest),"");
        txtwelcome.setText("Welcome, "+nama+".");
    }
    public void logout(View v){
        pref.edit().clear().apply();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
    public void Hapus(View v){
        if(MainActivity.chkgu){
            Toast toast = Toast.makeText(this,"Tidak dapat menghapus karena akun Guest",Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            DatabaseReference myref = database.getReference("User").child(nama);
            myref.removeValue();
            pref.edit().clear().apply();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }
    private void InitData(){
        this.IsiSoalTambah = new ArrayList<>();
        IsiSoalTambah.add(new Soal(" 5 + 1 = ","1","2","4","5","6",
                "6"));
        IsiSoalTambah.add(new Soal(" 0 + 1 = ","1","2","4","5","6",
                "1"));
        IsiSoalTambah.add(new Soal(" 2 + 0 = ","1","2","4","5","6",
                "2"));
        IsiSoalTambah.add(new Soal(" 3 + 2 = ","1","2","4","5","6",
                "5"));
        IsiSoalTambah.add(new Soal(" 3 + 3 = ","1","2","4","5","6",
                "6"));

        this.IsiSoalMinus=new ArrayList<>();
        IsiSoalMinus.add(new Soal("3 - 1 = ","1","2","3","4","5",
                "2"));
        IsiSoalMinus.add(new Soal("5 - 2 = ","1","2","3","4","5",
                "3"));
        IsiSoalMinus.add(new Soal("6 - 5 = ","1","2","3","4","5",
                "1"));
        IsiSoalMinus.add(new Soal("8 - 3 = ","1","2","3","4","5",
                "5"));
        IsiSoalMinus.add(new Soal("9 - 6 = ","1","2","3","4","5",
                "3"));

        this.IsiSoalKali=new ArrayList<>();
        IsiSoalKali.add(new Soal("3 x 3 = ","0","9","8","6","4",
                "9"));
        IsiSoalKali.add(new Soal("5 x 0 = ","0","2","3","4","5",
                "0"));
        IsiSoalKali.add(new Soal("6 x 5 = ","10","20","30","40","50",
                "30"));
        IsiSoalKali.add(new Soal("8 x 1 = ","9","8","7","6","5",
                "8"));
        IsiSoalKali.add(new Soal("123 x 2 = ","876","523","243","246","255",
                "246"));

        this.IsiSoalBagi=new ArrayList<>();
        IsiSoalBagi.add(new Soal("12 : 4 = ","1","2","3","4","5",
                "3"));
        IsiSoalBagi.add(new Soal("9 : 3 = ","1","2","3","4","5",
                "3"));
        IsiSoalBagi.add(new Soal("4 : 2 = ","1","2","3","4","5",
                "2"));
        IsiSoalBagi.add(new Soal("20 : 5 = ","1","2","3","4","5",
                "4"));
        IsiSoalBagi.add(new Soal("1 : 1 = ","1","2","3","4","5",
                "1"));

        this.IsiMateri=new ArrayList<>();
        IsiMateri.add(new Materi("Pertambahan",R.drawable.plus1,IsiSoalTambah));
        IsiMateri.add(new Materi("Pembagian",R.drawable.divide1,IsiSoalBagi));
        IsiMateri.add(new Materi("Pengurangan",R.drawable.minus1,IsiSoalMinus));
        IsiMateri.add(new Materi("Perkalian",R.drawable.multiply1,IsiSoalKali));
    }
}