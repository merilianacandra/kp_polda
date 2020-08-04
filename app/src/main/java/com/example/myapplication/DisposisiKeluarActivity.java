package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DisposisiKeluarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disposisi_keluar);
    }

    public void det_disposisi_keluar(View view) {
        Intent intent = new Intent(DisposisiKeluarActivity.this, DetailDisposisiKeluarActivity.class);
        startActivity(intent);
    }
}
