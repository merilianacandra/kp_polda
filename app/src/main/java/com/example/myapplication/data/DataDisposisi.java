package com.example.myapplication.data;

public class DataDisposisi {
    private String id_disposisi,id_surat, no_surat, asal, tujuan, tersier, derajat_surat, jenis_naskah_dinas, tgl_disposisi, perihal, lampiran, isi_disposisi, file_disposisi, no_agenda, nama, id_level;

    public DataDisposisi() {
    }

    public DataDisposisi(String id_disposisi, String id_surat, String no_surat, String tujuan, String tersier, String derajat_surat, String jenis_naskah_dinas, String asal, String tgl_disposisi, String perihal, String lampiran, String isi_disposisi, String file_disposisi, String no_agenda, String nama, String id_level) {
        this.id_disposisi = id_disposisi;
        this.id_surat = id_surat;
        this.no_surat = no_surat;
        this.tersier = tersier;
        this.derajat_surat = derajat_surat;
        this.jenis_naskah_dinas = jenis_naskah_dinas;
        this.asal = asal;
        this.tgl_disposisi = tgl_disposisi;
        this.tujuan = tujuan;
        this.perihal = perihal;
        this.lampiran = lampiran;
        this.isi_disposisi = isi_disposisi;
        this.file_disposisi = file_disposisi;
        this.no_agenda = no_agenda;
        this.nama= nama;
        this.id_level= id_level;
    }

    public String getId_surat() {
        return id_surat;
    }

    public void setId_surat(String id_surat) {
        this.id_surat = id_surat;
    }

    public String getNo_surat() {
        return no_surat;
    }

    public void setNo_surat(String no_surat) {
        this.no_surat = no_surat;
    }

    public String getId_disposisi() {
        return id_disposisi;
    }

    public void setId_disposisi(String id_disposisi) {
        this.id_disposisi= id_disposisi;
    }

    public String getTersier() {
        return tersier;
    }

    public void setTersier(String tersier) {
        this.tersier= tersier;
    }

    public String getDerajat_surat() {
        return derajat_surat;
    }

    public void setDerajat_surat(String derajat_surat) {
        this.derajat_surat = derajat_surat;
    }

    public String getJenis_naskah_dinas() {
        return jenis_naskah_dinas;
    }

    public void setJenis_naskah_dinas(String jenis_naskah_dinas) {
        this.jenis_naskah_dinas = jenis_naskah_dinas;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getTgl_disposisi() {
        return tgl_disposisi;
    }

    public void setTgl_disposisi(String tgl_disposisi) {
        this.tgl_disposisi = tgl_disposisi;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public String getIsi_disposisi() {
        return isi_disposisi;
    }

    public void setIsi_disposisi(String isi_disposisi) {
        this.isi_disposisi = isi_disposisi;
    }

    public String getFile_disposisi() {
        return file_disposisi;
    }

    public void setFile_disposisi(String file_disposisi) {
        this.file_disposisi = file_disposisi;
    }

    public String getNo_agenda() {
        return no_agenda;
    }

    public void setNo_agenda(String no_agenda) {
        this.no_agenda = no_agenda;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId_level() {
        return id_level;
    }

    public void setId_level(String id_level) {
        this.id_level = id_level;
    }
}
