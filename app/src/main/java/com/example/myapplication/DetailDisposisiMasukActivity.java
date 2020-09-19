package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.app.AppController;
import com.example.myapplication.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class DetailDisposisiMasukActivity extends AppCompatActivity {

    TextView tno_surat, ttujuan, tasal, tisi_disposisi, tperihal, tderajat_surat, ttanggal,tno_agenda,tkode_arsip,tjenis_naskah_dinas,tlampiran, tstatus_buat;
    String id_disposisi, id_surat,ptujuan, pno_surat,pasal,pisi_disposisi,pperihal, pderajat_surat, ptanggal, pno_agenda, pkode_arsip, pjenis_naskah_dinas, plampiran, status_buat;
    String id;
    Button btn_disposisi;
    int pstatus_buat, id_pegawai, id_tujuan;
    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id";
    int success;
    private static final String TAG = DetailDisposisiMasukActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static String url_update_baca_disposisi	 = Server.URL + "update_baca_disposisi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_disposisi_masuk);

        sharedpreferences = this.getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        id = sharedpreferences.getString(TAG_ID,"");

        btn_disposisi = (Button) findViewById(R.id.btn_disposisi);
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
        tstatus_buat = (TextView) findViewById(R.id.status_buat);

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
        pstatus_buat = Integer.parseInt(getIntent().getStringExtra("status_buat"));

        if (pstatus_buat == 1){
            status_buat = "Belum Didisposisi";
        } if (pstatus_buat == 2){
            status_buat = "Sudah Didisposisi";
        }

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
        tstatus_buat.setText(status_buat);

        id_pegawai = Integer.parseInt(id);
        id_tujuan = Integer.parseInt(getIntent().getStringExtra("id_tujuan"));
        if(id_tujuan == id_pegawai){
            update_baca_disposisi();

         }

        if (id_tujuan == id_pegawai) {
            btn_disposisi.setVisibility(View.VISIBLE);
            //Digunakan untuk menyembunyikan TextView dan ImageView
        } else {
            btn_disposisi.setVisibility(View.GONE);
            //Digunakan untuk menampilkan lagi  TextView dan ImageView
        }
    }

    public void buat_disposisi(View view) {
        Intent intent = new Intent(DetailDisposisiMasukActivity.this, FormDisposisi2Activity.class);
        intent.putExtra("id_surat", id_surat);
        intent.putExtra("no_agenda", pno_agenda);
        intent.putExtra("no_surat", pno_surat);
        intent.putExtra("derajat_surat", pderajat_surat);
        intent.putExtra("perihal", pperihal);
        intent.putExtra("isi_disposisi", pisi_disposisi);
        intent.putExtra("id_disposisi", id_disposisi);
        startActivity(intent);
    }

    private void update_baca_disposisi() {
//        String url;
//        // jika id kosong maka simpan, jika id ada nilainya maka update
//        if (id.isEmpty()){
//            url = url_insert;
//        } else {
//            url = url_update;
//        }

        StringRequest strReq = new StringRequest(Request.Method.POST, url_update_baca_disposisi, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("update status_baca", jObj.toString());

////                        callVolley();
////                        kosong();
////
//                        Toast.makeText(DetailDisposisiMasukActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
////                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(DetailDisposisiMasukActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(DetailDisposisiMasukActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                params.put("id_disposisi", String.valueOf(id_disposisi));


                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}
