package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailSuratMasukActivityActivity extends AppCompatActivity {

    TextView tno_surat, tasal, tisi_ringkas, tperihal, tderajat_surat, ttanggal,tno_agenda,tkode_arsip,tjenis_naskah_dinas,tlampiran;
    String id_surat,pno_surat,pasal,pisi_ringkas,pperihal, pderajat_surat, ptanggal, pno_agenda, pkode_arsip, pjenis_naskah_dinas, plampiran;
    String id;
    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id";
    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_surat_masuk_activity);

        sharedpreferences = this.getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        id = sharedpreferences.getString(TAG_ID,"");

        tno_surat= (TextView) findViewById(R.id.no_surat);
        tno_agenda = (TextView) findViewById(R.id.no_agenda);
        tasal= (TextView) findViewById(R.id.asal);
        tisi_ringkas= (TextView) findViewById(R.id.isi_ringkas);
        tperihal= (TextView) findViewById(R.id.perihal);
        tderajat_surat = (TextView) findViewById(R.id.derajat_surat);
        ttanggal = (TextView) findViewById(R.id.tanggal);
        tkode_arsip = (TextView) findViewById(R.id.kode_arsip);
        tjenis_naskah_dinas = (TextView) findViewById(R.id.jenis_naskah_dinas);
        tlampiran = (TextView) findViewById(R.id.lampiran);

        id_surat= getIntent().getStringExtra("id_surat");
        pno_surat=getIntent().getStringExtra("no_surat");
        pno_agenda=getIntent().getStringExtra("no_agenda");
        pasal=getIntent().getStringExtra("asal");
        pisi_ringkas=getIntent().getStringExtra("isi");
        pperihal = getIntent().getStringExtra("perihal");
        pderajat_surat = getIntent().getStringExtra("derajat_surat");
        ptanggal = getIntent().getStringExtra("tanggal");
        pkode_arsip = getIntent().getStringExtra("tersier");
        pjenis_naskah_dinas = getIntent().getStringExtra("jenis_naskah_dinas");
        plampiran = getIntent().getStringExtra("lampiran");

        tno_surat.setText(pno_surat);
        tno_agenda.setText(pno_agenda);
        tasal.setText(pasal);
        tisi_ringkas.setText(pisi_ringkas);
        tperihal.setText(pperihal);
        tderajat_surat.setText(pderajat_surat);
        ttanggal.setText(ptanggal);
        tkode_arsip.setText(pkode_arsip);
        tjenis_naskah_dinas.setText(pjenis_naskah_dinas);
        tlampiran.setText(plampiran);
    }

    public void buat_disposisi(View view) {
        Intent intent = new Intent(DetailSuratMasukActivityActivity.this, FormDisposisiActivity.class);
        intent.putExtra("id_surat", id_surat);
        intent.putExtra("no_agenda", pno_agenda);
        intent.putExtra("no_surat", pno_surat);
        intent.putExtra("derajat_surat", pderajat_surat);
        intent.putExtra("perihal", pperihal);
        startActivity(intent);
    }
}
