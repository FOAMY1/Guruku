package com.foamy.guruku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SoalPage extends AppCompatActivity {
    String bernama, scorestring; public static String lastscore;
    Soal a;
    int idnyagmbr, score, gonext;
    public static ArrayList<Soal> listpilgan = new ArrayList<>();
    TextView textview, textview2, textview3;
    RadioGroup JawabanGrup;
    RadioButton jw1, jw2, jw3, jw4, jw5;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_page);
        GetIncomingIntent();
        textview = findViewById(R.id.alah);
        textview2 = findViewById(R.id.alah2);
        textview3 = findViewById(R.id.textViewscore);
        JawabanGrup = findViewById(R.id.Jawabangroup);
        jw1 = findViewById(R.id.Jawaban1);
        jw2 = findViewById(R.id.Jawaban2);
        jw3 = findViewById(R.id.Jawaban3);
        jw4 = findViewById(R.id.Jawaban4);
        jw5 = findViewById(R.id.Jawaban5);
        pref = this.getSharedPreferences(getString(R.string.UsernameG), Context.MODE_PRIVATE);
        score = 0;
        gonext = 0;
        textview.setText(bernama);
        a = listpilgan.get(0);
        textview2.setText(a.getSoal());
        jw1.setText(a.getJawaban1()); jw2.setText(a.getJawaban2()); jw3.setText(a.getJawaban3()); jw4.setText(a.getJawaban4()); jw5.setText(a.getJawaban5());
    }
    private void GetIncomingIntent(){
        if (getIntent().hasExtra("jeneng") && getIntent().hasExtra("idgmbr")&& getIntent().hasExtra("pilihangan")){
            bernama = getIntent().getStringExtra("jeneng");
            idnyagmbr = getIntent().getIntExtra("idgmbr",0);
            listpilgan = (ArrayList<Soal>) getIntent().getSerializableExtra("pilihangan");
        }
    }

    public void GantiSoal(View v){
        String Jawabanb = a.getKodebenar();
        if(JawabanGrup.getCheckedRadioButtonId()!=-1) {
            gonext = gonext + 1;
            int selectedId = JawabanGrup.getCheckedRadioButtonId();
            RadioButton jwuser = findViewById(selectedId);
            if (gonext >=5) {
                gonext=0;
                if (Jawabanb.equals(jwuser.getText().toString())) {
                    score = score + 1;
                }
                scorestring = Integer.toString(score);
                textview3.setText("Score : " + scorestring);
                if (bernama.equalsIgnoreCase("pertambahan")) {
                    lastscore = pref.getString(getString(R.string.pertambahan),"0");
                    pref.edit().putString(getString(R.string.pertambahan), scorestring).apply();
                } else if (bernama.equalsIgnoreCase("pengurangan")) {
                    lastscore = pref.getString(getString(R.string.pengurangan),"0");
                    pref.edit().putString(getString(R.string.pengurangan), scorestring).apply();
                } else if (bernama.equalsIgnoreCase("pembagian")) {
                    lastscore = pref.getString(getString(R.string.pembagian),"0");
                    pref.edit().putString(getString(R.string.pembagian), scorestring).apply();
                } else {
                    lastscore = pref.getString(getString(R.string.perkalian),"0");
                    pref.edit().putString(getString(R.string.perkalian), scorestring).apply();
                }
                score=0;
                Intent intent;
                intent = new Intent(this, ShowScore.class);
                intent.putExtra("kode",bernama);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
                if (Jawabanb.equals(jwuser.getText().toString())) {
                    a = listpilgan.get(gonext);
                    textview2.setText(a.getSoal());
                    score = score + 1;
                    jw1.setText(a.getJawaban1());
                    jw2.setText(a.getJawaban2());
                    jw3.setText(a.getJawaban3());
                    jw4.setText(a.getJawaban4());
                    jw5.setText(a.getJawaban5());
                    JawabanGrup.clearCheck();
                    scorestring = Integer.toString(score);
                    textview3.setText("Score : " + scorestring);
                } else {
                    a = listpilgan.get(gonext);
                    textview2.setText(a.getSoal());
                    jw1.setText(a.getJawaban1());
                    jw2.setText(a.getJawaban2());
                    jw3.setText(a.getJawaban3());
                    jw4.setText(a.getJawaban4());
                    jw5.setText(a.getJawaban5());
                    JawabanGrup.clearCheck();
                    scorestring = Integer.toString(score);
                    textview3.setText("Score : " + scorestring);
                }
            }
        }
        else{
            Toast toast = Toast.makeText(this,"Harus memilih jawaban",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}