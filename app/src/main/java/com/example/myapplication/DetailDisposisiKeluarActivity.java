package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailDisposisiKeluarActivity extends AppCompatActivity {

    TextView tno_surat, ttujuan, tasal, tisi_disposisi, tperihal, tderajat_surat, ttanggal,tno_agenda,tkode_arsip,tjenis_naskah_dinas,tlampiran;
    String id_disposisi, id_surat,ptujuan, pno_surat,pasal,pisi_disposisi,pperihal, pderajat_surat, ptanggal, pno_agenda, pkode_arsip, pjenis_naskah_dinas, plampiran;
    String id;
    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id";
    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_disposisi_keluar);

        sharedpreferences = this.getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        id = sharedpreferences.getString(TAG_ID,"");

        tno_surat= (TextView) findViewById(R.id.no_surat);
        tno_agenda = (TextView) findViewById(R.id.no_agenda);
        tasal= (TextView) findViewById(R.id.asal);
        tisi_disposisi= (TextView) findViewById(R.id.isi_disposisi);
        tperihal= (TextView) findViewById(R.id.perihal);
        tderajat_surat = (TextView) findViewById(R.id.derajat);
        ttanggal = (TextView) findViewById(R.id.tanggal);
        tkode_arsip = (TextView) findViewById(R.id.kode_arsip);
        tjenis_naskah_dinas = (TextView) findViewById(R.id.jenis_naskah_dinas);
        tlampiran = (TextView) findViewById(R.id.lampiran);
        ttujuan = (TextView) findViewById(R.id.tujuan);

        id_disposisi= getIntent().getStringExtra("id_disposisi");
        id_surat= getIntent().getStringExtra("id_surat");
        pno_surat=getIntent().getStringExtra("no_surat");
        pno_agenda=getIntent().getStringExtra("no_agenda");
        pasal=getIntent().getStringExtra("nama_asal");
        pisi_disposisi=getIntent().getStringExtra("isi");
        pperihal = getIntent().getStringExtra("perihal");
        pderajat_surat = getIntent().getStringExtra("derajat_surat");
        ptanggal = getIntent().getStringExtra("tanggal");
        pkode_arsip = getIntent().getStringExtra("tersier");
        pjenis_naskah_dinas = getIntent().getStringExtra("jenis_naskah_dinas");
        plampiran = getIntent().getStringExtra("lampiran");
        ptujuan = getIntent().getStringExtra("tujuan");

        tno_surat.setText(pno_surat);
        tno_agenda.setText(pno_agenda);
        tasal.setText(pasal);
        tisi_disposisi.setText(pisi_disposisi);
        tperihal.setText(pperihal);
        tderajat_surat.setText(pderajat_surat);
        ttanggal.setText(ptanggal);
        tkode_arsip.setText(pkode_arsip);
        tjenis_naskah_dinas.setText(pjenis_naskah_dinas);
        tlampiran.setText(plampiran);
        ttujuan.setText(ptujuan);
    }
}
