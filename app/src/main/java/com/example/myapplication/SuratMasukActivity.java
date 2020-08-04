package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuratMasukActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surat_masuk_activity);
    }

    public void det_surat_masuk(View view) {
        Intent intent = new Intent(SuratMasukActivity.this, DetailSuratMasukActivityActivity.class);
        startActivity(intent);
    }
}
