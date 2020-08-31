package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReferensiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referensi);
    }

    public void ref_pegawai(View view) {
        Intent intent = new Intent(ReferensiActivity.this, ReferensiPegawaiActivity.class);
        startActivity(intent);
    }

    public void ref_klasifikasi(View view) {
        Intent intent = new Intent(ReferensiActivity.this, ReferensiKlasifikasiSuratActivity.class);
        startActivity(intent);
    }

    public void ref_kodearsip(View view) {
        Intent intent = new Intent(ReferensiActivity.this, ReferensiKodeArsipActivity.class);
        startActivity(intent);
    }

    public void ref_naskah(View view) {
        Intent intent = new Intent(ReferensiActivity.this, ReferensiNaskahDinasActivity.class);
        startActivity(intent);
    }

    public void ref_derajat_surat(View view) {
        Intent intent = new Intent(ReferensiActivity.this, ReferensiDerajatSuratActivity.class);
        startActivity(intent);
    }
}
