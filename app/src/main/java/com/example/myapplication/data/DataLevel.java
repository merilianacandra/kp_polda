package com.example.myapplication.data;

public class DataLevel {
    private String nama, jabatan, id_pegawai;
    private boolean check;

    public DataLevel() {}

    public DataLevel(String nama, String jabatan, String id_pegawai, boolean check) {
        this.nama = nama;
        this.jabatan = jabatan;
        this.id_pegawai = id_pegawai;
        this.check = check;
    }

    public String getLevel() {
        return nama;
    }

    public void setLevel(String nama) {
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public boolean isCheckbox() {
        return check;
    }

    public void setCheckbox(boolean check) {
        this.check = check;
    }
}