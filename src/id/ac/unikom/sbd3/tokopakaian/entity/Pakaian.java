/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.unikom.sbd3.tokopakaian.entity;

/**
 *
 * @author Iqram
 */
public class Pakaian {
    private String kodePakaian;
    private String nama;
    private String katPakaian;
    private int harga;
    private int stok;

    public String getKodePakaian() {
        return kodePakaian;
    }

    public void setKodePakaian(String kodePakaian) {
        this.kodePakaian = kodePakaian;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKatPakaian() {
        return katPakaian;
    }

    public void setKatPakaian(String katPakaian) {
        this.katPakaian = katPakaian;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
    
    
}
