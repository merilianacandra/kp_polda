package com.example.myapplication.data;

public class DataLevel {
    private String level;
    private boolean check;

    public DataLevel() {}

    public DataLevel(String level, boolean check) {
        this.level = level;
        this.check = check;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isCheckbox() {
        return check;
    }

    public void setCheckbox(boolean check) {
        this.check = check;
    }
}