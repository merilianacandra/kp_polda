package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SuratKeluarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surat_keluar_activity);
    }

    public void det_surat_keluar(View view) {
        Intent intent = new Intent(SuratKeluarActivity.this, DetailSuratKeluarActivity.class);
        startActivity(intent);
    }
}
