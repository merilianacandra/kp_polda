package com.example.myapplication.data;

public class DataProfil {
    private String id_pegawai, nrp, nama, tgl_lahir, alamat, jenis_kelamin, no_hp, pass, id_pangkat, id_jabatan, id_hak_akses, foto, id_level, pangkat, jabatan;

    public DataProfil() {
    }

    public DataProfil(String id_pegawai, String nrp, String nama, String tgl_lahir, String alamat, String jenis_kelamin, String no_hp, String pass, String id_pangkat, String id_jabatan, String id_hak_akses, String foto, String id_level, String pangkat, String jabatan) {
        this.id_pegawai = id_pegawai;
        this.nrp = nrp;
        this.nama = nama;
        this.alamat = alamat;
        this.tgl_lahir = tgl_lahir;
        this.jenis_kelamin = jenis_kelamin;
        this.no_hp = no_hp;
        this.pass = pass;
        this.id_hak_akses = id_hak_akses;
        this.id_pangkat = id_pangkat;
        this.id_jabatan = id_jabatan;
        this.foto = foto;
        this.id_level = id_level;
        this.pangkat = pangkat;
        this.jabatan = jabatan;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        this.nrp = nrp;
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

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getId_hak_akses() {
        return id_hak_akses;
    }

    public void setId_hak_akses(String id_hak_akses) {
        this.id_hak_akses = id_hak_akses;
    }

    public String getId_pangkat() {
        return id_pangkat;
    }

    public void setId_pangkat(String id_pangkat) {
        this.id_pangkat = id_pangkat;
    }

    public String getId_jabatan() {
        return id_jabatan;
    }

    public void setId_jabatan(String id_jabatan) {
        this.id_jabatan = id_jabatan;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId_level() {
        return id_level;
    }

    public void setId_level(String id_level) {
        this.id_level = id_level;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getPangkat() {
        return pangkat;
    }

    public void setPangkat(String pangkat) {
        this.pangkat = pangkat;
    }
}
