package com.example.arief.uaskasir.custom.recycleviewSupplier;

public class Supplier {
    public int id;
    public String nama,alamat,nomer;
    public Supplier(){
    }
    public Supplier(int id, String nama, String alamat,String nomer) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.nomer = nomer;
    }
    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getNama() {

        return nama;
    }

    public void setNama(String nama) {

        this.nama = nama;
    }

    public String getAlamat() {

        return alamat;
    }

    public void setAlamat(String alamat) {

        this.alamat = alamat;
    }

    public String getNomer() {

        return nomer;
    }

    public void setNomer(String nomer) {

        this.nomer = nomer;
    }

}
