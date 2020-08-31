package com.example.myapplication.data;

public class DataLevel {
    private String nama;
    private boolean check;

    public DataLevel() {}

    public DataLevel(String nama, boolean check) {
        this.nama = nama;
        this.check = check;
    }

    public String getLevel() {
        return nama;
    }

    public void setLevel(String nama) {
        this.nama = nama;
    }

    public boolean isCheckbox() {
        return check;
    }

    public void setCheckbox(boolean check) {
        this.check = check;
    }
}