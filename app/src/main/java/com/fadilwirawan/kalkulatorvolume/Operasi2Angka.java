package com.fadilwirawan.kalkulatorvolume;

public class Operasi2Angka {
    private double angka1, angka2;

    public Operasi2Angka(){
    }

    public void setAngka1(double angka1){this.angka1 = angka1;}
    public void setAngka2(double angka2){this.angka2 = angka2;}
    public double penjumlahan(){return angka1+angka2;}
    public double pengurangan(){return angka1-angka2;}
    public double perkalian(){return angka1*angka2;}
    public double pembagian(){return angka1/angka2;}
}
