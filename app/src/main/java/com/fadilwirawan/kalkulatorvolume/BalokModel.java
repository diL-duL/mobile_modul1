package com.fadilwirawan.kalkulatorvolume;

public class BalokModel {
    // Atribut dibuat private (Encapsulation)
    private double panjang;
    private double lebar;
    private double tinggi;

    //  constructor untuk menginisialisasi objek
    public BalokModel(){
    }

    //  setter untuk mengatur nilai atribut
    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }

    public void setTinggi(double tinggi) {
        this.tinggi = tinggi;
    }

    //  method untuk melakukan perhitungan
    public double hitungVolume(){
        return panjang*lebar*tinggi;
    }
}
