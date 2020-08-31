package com.example.myapplication.data;

public class DataLevelPegawai {
    private String level_pegawai;
    private boolean check;

    public DataLevelPegawai() {}

    public DataLevelPegawai(String level_pegawai, boolean check) {
        this.level_pegawai = level_pegawai;
        this.check = check;
    }

    public String getLevelPegawai() {
        return level_pegawai;
    }

    public void setLevelPegawai(String level_pegawai) {
        this.level_pegawai = level_pegawai;
    }

    public boolean isCheckbox() {
        return check;
    }

    public void setCheckbox(boolean check) {
        this.check = check;
    }
}