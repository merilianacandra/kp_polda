package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormDisposisiActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] pendidikan = {"PILIHAN", "Kasubag Renmin", "Kasubag Tekkom", "Kasubag Tekinfo"};
    TextView tno_surat, tperihal, tderajat_surat, tno_agenda;
    String id_surat,pno_surat,pperihal, pderajat_surat,  pno_agenda;
    String id;
    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id";
    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_disposisi);

        sharedpreferences = this.getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        id = sharedpreferences.getString(TAG_ID,"");

        tno_surat= (TextView) findViewById(R.id.form_nomorsurat);
        tno_agenda = (TextView) findViewById(R.id.form_nomoragenda);
        tperihal= (TextView) findViewById(R.id.form_perihal);
        tderajat_surat = (TextView) findViewById(R.id.form_kategori);

        id_surat= getIntent().getStringExtra("id_surat");
        pno_surat=getIntent().getStringExtra("no_surat");
        pno_agenda=getIntent().getStringExtra("no_agenda");
        pperihal = getIntent().getStringExtra("perihal");
        pderajat_surat = getIntent().getStringExtra("derajat_surat");

        tno_surat.setText(pno_surat);
        tno_agenda.setText(pno_agenda);
        tperihal.setText(pperihal);
        tderajat_surat.setText(pderajat_surat);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pendidikan);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    public void onCheckboxClicked(View view) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getApplicationContext(), pendidikan[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void simpan_disposisi(View view) {
        Intent intent = new Intent(FormDisposisiActivity.this, MainActivity.class);
        intent.putExtra("id_surat", id_surat);
        intent.putExtra("no_agenda", pno_agenda);
        intent.putExtra("no_surat", pno_surat);
        intent.putExtra("derajat_surat", pderajat_surat);
        intent.putExtra("perihal", pperihal);
        startActivity(intent);
    }
}
