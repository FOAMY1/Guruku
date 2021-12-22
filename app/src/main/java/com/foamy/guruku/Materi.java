package com.foamy.guruku;

import java.util.ArrayList;

public class Materi {
    private String NamaMateri;
    private int IdGambar;
    private ArrayList<Soal> pilgan;

    public Materi(String namaMateri, int idGambar, ArrayList<Soal> pilgan) {
        NamaMateri = namaMateri;
        IdGambar = idGambar;
        this.pilgan = pilgan;
    }

    public String getNamaMateri() {
        return NamaMateri;
    }

    public void setNamaMateri(String namaMateri) {
        NamaMateri = namaMateri;
    }

    public int getIdGambar() {
        return IdGambar;
    }

    public void setIdGambar(int idGambar) {
        IdGambar = idGambar;
    }

    public ArrayList<Soal> getPilgan() {
        return pilgan;
    }

    public void setPilgan(ArrayList<Soal> pilgan) {
        this.pilgan = pilgan;
    }
}
