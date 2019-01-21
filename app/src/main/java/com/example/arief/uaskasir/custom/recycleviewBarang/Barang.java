package com.example.arief.uaskasir.custom.recycleviewBarang;

public class Barang {
    public int id;
    public String nama;
    public int hargabeli;
    public int hargajual;
//    public String hargajual;
    public int stok;
    public int kdsipplier;
    public Barang() {

    }
    public Barang(int id, String nama, int hargabeli, int hargajual, int stok, int kdsipplier) {
        this.id = id;
        this.nama = nama;
        this.hargabeli = hargabeli;
        this.hargajual = hargajual;
        this.stok = stok;
        this.kdsipplier = kdsipplier;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getHargabeli() {
        return hargabeli;
    }

    public int getHargajual() {
        return hargajual;
    }
//    public String getHargajual() {
//        return hargajual;
//    }

    public int getStok() {
        return stok;
    }

    public int getKdsipplier() {
        return kdsipplier;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHargabeli(int hargabeli) {
        this.hargabeli = hargabeli;
    }

//    public void setHargajual(int hargajual) {
//        this.hargajual = hargajual;
//    }
    public void setHargajual(int hargajual) {
        this.hargajual = hargajual;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setKdsipplier(int kdsipplier) {
        this.kdsipplier = kdsipplier;
    }
}
