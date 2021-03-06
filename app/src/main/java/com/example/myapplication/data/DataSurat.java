package com.example.myapplication.data;

public class DataSurat {
    private String id_surat, no_surat, asal, klasifikasi_surat, tersier, derajat_surat, jenis_naskah_dinas, tgl_surat, tujuan, perihal, lampiran, isi_ringkas, file_surat, no_agenda, nama, id_level, nama_satker, keterangan_instansi, status;

    public DataSurat() {
    }

    public DataSurat(String id_surat, String no_surat, String klasifikasi_surat, String tersier, String derajat_surat, String jenis_naskah_dinas, String asal, String tgl_surat, String tujuan, String perihal, String lampiran, String isi_ringkas, String file_surat, String no_agenda, String nama, String id_level, String nama_satker, String keterangan_instansi, String status) {
        this.id_surat = id_surat;
        this.no_surat = no_surat;
        this.klasifikasi_surat = klasifikasi_surat;
        this.tersier = tersier;
        this.derajat_surat = derajat_surat;
        this.jenis_naskah_dinas = jenis_naskah_dinas;
        this.asal = asal;
        this.tgl_surat = tgl_surat;
        this.tujuan = tujuan;
        this.perihal = perihal;
        this.lampiran = lampiran;
        this.isi_ringkas = isi_ringkas;
        this.file_surat = file_surat;
        this.no_agenda = no_agenda;
        this.nama= nama;
        this.id_level= id_level;
        this.nama_satker= nama_satker;
        this.keterangan_instansi= keterangan_instansi;
        this.status= status;
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

    public String getKlasifikasi_surat() {
        return klasifikasi_surat;
    }

    public void setKlasifikasi_surat(String klasifikasi_surat) {
        this.klasifikasi_surat = klasifikasi_surat;
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

    public String getTgl_surat() {
        return tgl_surat;
    }

    public void setTgl_surat(String tgl_surat) {
        this.tgl_surat = tgl_surat;
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

    public String getIsi_ringkas() {
        return isi_ringkas;
    }

    public void setIsi_ringkas(String isi_ringkas) {
        this.isi_ringkas = isi_ringkas;
    }

    public String getFile_surat() {
        return file_surat;
    }

    public void setFile_surat(String file_surat) {
        this.file_surat = file_surat;
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

    public String getNama_satker() {
        return nama_satker;
    }

    public void setNama_satker(String nama_satker) {
        this.nama_satker = nama_satker;
    }

    public String getKeterangan_instansi() {
        return keterangan_instansi;
    }

    public void setKeterangan_instansi(String keterangan_instansi) {
        this.keterangan_instansi = keterangan_instansi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
