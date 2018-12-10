package com.example.arief.uaskasir.custom.RecycleviewKaryawan;

public class Karyawan {
    public int id;
    public String nama,alamat,email,nomer;

    public Karyawan() {
    }

    public Karyawan(int id, String nama, String alamat, String email, String nomer) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.nomer = nomer;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }

    public String getNomer() {
        return nomer;
    }
}
