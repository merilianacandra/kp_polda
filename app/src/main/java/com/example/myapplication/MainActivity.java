package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;

    TextView txt_username, txt_jabatan;
    String id, username,jabatan;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_JABATAN = "jabatan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout=(GridLayout)findViewById(R.id.mainGrid);

        txt_username = (TextView) findViewById(R.id.textnama);
        txt_jabatan = (TextView) findViewById(R.id.textjabatan);

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        id = getIntent().getStringExtra(TAG_ID);
        username = getIntent().getStringExtra(TAG_USERNAME);
        jabatan = getIntent().getStringExtra(TAG_JABATAN);

        txt_username.setText("USERNAME : " + username);
        txt_jabatan.setText("JABATAN : " + jabatan);
        setSingleEvent(gridLayout);

    }

    private void setSingleEvent(GridLayout gridLayout) {
        for(int i = 0; i<gridLayout.getChildCount();i++){
            CardView cardView=(CardView)gridLayout.getChildAt(i);
            final int finalI= i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,"Clicked at index "+ finalI,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void surat_keluar(View view) {
        Intent intent = new Intent(MainActivity.this, SuratKeluarActivity.class);
        startActivity(intent);
    }

    public void surat_masuk(View view) {
        Intent intent = new Intent(MainActivity.this, SuratMasukActivity.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void referensi(View view) {
        Intent intent = new Intent(MainActivity.this, ReferensiActivity.class);
        startActivity(intent);
    }

    public void statistik(View view) {
        Intent intent = new Intent(MainActivity.this, StatistikActivity.class);
        startActivity(intent);
    }

    public void disposisi_keluar(View view) {
        Intent intent = new Intent(MainActivity.this, DisposisiKeluarActivity.class);
        startActivity(intent);
    }

    public void disposisi_masuk(View view) {
        Intent intent = new Intent(MainActivity.this, DisposisiMasukActivity.class);
        startActivity(intent);
    }
}
