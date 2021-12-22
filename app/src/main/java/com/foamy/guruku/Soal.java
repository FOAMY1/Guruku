package com.foamy.guruku;

import java.io.Serializable;

public class Soal implements Serializable {
    private String soal,jawaban1,jawaban2,jawaban3,jawaban4,jawaban5;
    private String kodebenar;

    public Soal(String soal, String jawaban1, String jawaban2, String jawaban3, String jawaban4, String jawaban5, String kodebenar) {
        this.soal = soal;
        this.jawaban1 = jawaban1;
        this.jawaban2 = jawaban2;
        this.jawaban3 = jawaban3;
        this.jawaban4 = jawaban4;
        this.jawaban5 = jawaban5;
        this.kodebenar = kodebenar;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getJawaban1() {
        return jawaban1;
    }

    public void setJawaban1(String jawaban1) {
        this.jawaban1 = jawaban1;
    }

    public String getJawaban2() {
        return jawaban2;
    }

    public void setJawaban2(String jawaban2) {
        this.jawaban2 = jawaban2;
    }

    public String getJawaban3() {
        return jawaban3;
    }

    public void setJawaban3(String jawaban3) {
        this.jawaban3 = jawaban3;
    }

    public String getJawaban4() {
        return jawaban4;
    }

    public void setJawaban4(String jawaban4) {
        this.jawaban4 = jawaban4;
    }

    public String getJawaban5() {
        return jawaban5;
    }

    public void setJawaban5(String jawaban5) {
        this.jawaban5 = jawaban5;
    }

    public String getKodebenar(){return kodebenar;}

    public void setKodebenar(String kodebenar) {
        this.kodebenar = kodebenar;
    }
}
