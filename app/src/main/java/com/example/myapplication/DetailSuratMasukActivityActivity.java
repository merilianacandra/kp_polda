package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.adapter.DisposisiMasukAdapter;
import com.example.myapplication.adapter.DisposisiSuratAdapter;
import com.example.myapplication.app.AppController;
import com.example.myapplication.data.DataDisposisi;
import com.example.myapplication.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class DetailSuratMasukActivityActivity extends AppCompatActivity {

    TextView tno_surat, tasal, tisi_ringkas, tperihal, tderajat_surat, ttanggal,tno_agenda,tkode_arsip,tjenis_naskah_dinas,tlampiran, tketerangan_instansi, tstatus;
    String id_surat,pno_surat,pasal,pisi_ringkas,pperihal, pderajat_surat, ptanggal, pno_agenda, pkode_arsip, pjenis_naskah_dinas, plampiran, pnama_satker, pketerangan_instansi, pstatus, status_disposisi;
    int status, id_tujuan,id_pegawai;
    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id";
    int success;
    Button btn_disposisi;
    private static final String TAG = DetailSuratMasukActivityActivity.class.getSimpleName();
    private String URLstring = Server.URL + "select_disposisi_surat.php";
    private static String url_update_baca_surat 	 = Server.URL + "update_baca_surat.php";
    private static String url_select_jumlah_disposisi	 = Server.URL + "select_jumlah_disposisi_surat.php";
    private static ProgressDialog mProgressDialog;
    List<DataDisposisi> DisposisiList = new ArrayList<>();
    private com.example.myapplication.adapter.DisposisiSuratAdapter DisposisiSuratAdapter;
    private RecyclerView recyclerView;
    Context mContext;
    String id_level, username, id;
    String tag_json_obj = "json_obj_req";
    public static final String TAG_USERNAME = "username";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_surat_masuk_activity);

        sharedpreferences = this.getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        id = sharedpreferences.getString(TAG_ID,"");
        btn_disposisi = (Button) findViewById(R.id.btn_disposisi);
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
        tketerangan_instansi = (TextView) findViewById(R.id.keterangan_instansi);
        tstatus = (TextView) findViewById(R.id.ket_disposisi);

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
        pnama_satker = getIntent().getStringExtra("nama_satker");
        pketerangan_instansi = getIntent().getStringExtra("keterangan_instansi");
        pstatus = getIntent().getStringExtra("status");
        id_tujuan = Integer.parseInt(getIntent().getStringExtra("id_tujuan"));
        id_pegawai = Integer.parseInt(id);
        update_baca_surat();





        tno_surat.setText(pno_surat);
        tno_agenda.setText(pno_agenda);
        tasal.setText(pnama_satker);
        tisi_ringkas.setText(pisi_ringkas);
        tperihal.setText(pperihal);
        tderajat_surat.setText(pderajat_surat);
        ttanggal.setText(ptanggal);
        tkode_arsip.setText(pkode_arsip);
        tjenis_naskah_dinas.setText(pjenis_naskah_dinas);
        tlampiran.setText(plampiran);
        if (pketerangan_instansi != null) {
            tketerangan_instansi.setText(pketerangan_instansi);
            //Digunakan untuk menyembunyikan TextView dan ImageView
        } else {
            tketerangan_instansi.setVisibility(View.GONE);
            //Digunakan untuk menampilkan lagi  TextView dan ImageView
        }
        if (id_tujuan == id_pegawai) {
            btn_disposisi.setVisibility(View.VISIBLE);
            //Digunakan untuk menyembunyikan TextView dan ImageView
        } else {
            btn_disposisi.setVisibility(View.GONE);
            //Digunakan untuk menampilkan lagi  TextView dan ImageView
        }
        select_jumlah_disposisi(Integer.parseInt(id_surat));
        recyclerView = findViewById(R.id.recyclerView1);
        mContext = this;
        username = sharedpreferences.getString(TAG_USERNAME,"");
        DisposisiSuratAdapter = new DisposisiSuratAdapter(mContext, DisposisiList);
        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 1);
        recyclerView.setLayoutManager(mLayoutManager);
//        id_kat_produk ="2";



        select_disposisi(Integer.parseInt(id_surat));
    }

    private void select_jumlah_disposisi(final int idx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_select_jumlah_disposisi, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt("success");

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get select data", jObj.toString());
                        String jumlah_disposisi    = jObj.getString("jumlah_disposisi");
//                        String idx    = jObj.getString("id_transaksi");
                        status= Integer.parseInt(jumlah_disposisi);
                        if (status > 0) {
                            status_disposisi="Sudah Didisposisi";
                        } else {
                            status_disposisi="Belum Didisposisi";
                        }
                        tstatus.setText(status_disposisi);
//                        total = Integer.parseInt(totalx);

                    } else {
                        Toast.makeText(DetailSuratMasukActivityActivity.this, jObj.getString("message"), Toast.LENGTH_LONG).show();
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
                Toast.makeText(DetailSuratMasukActivityActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_surat", String.valueOf(idx));

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void update_baca_surat() {
//        String url;
//        // jika id kosong maka simpan, jika id ada nilainya maka update
//        if (id.isEmpty()){
//            url = url_insert;
//        } else {
//            url = url_update;
//        }

        StringRequest strReq = new StringRequest(Request.Method.POST, url_update_baca_surat, new Response.Listener<String>() {

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
//                        Toast.makeText(DetailSuratMasukActivityActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
////                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(DetailSuratMasukActivityActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(DetailSuratMasukActivityActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                params.put("id_surat", String.valueOf(id_surat));


                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
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

    private void select_disposisi(final int idx){
        StringRequest strReq = new StringRequest(Request.Method.POST, URLstring, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("strrrrr", ">>" + response);

                try {

                    removeSimpleProgressDialog();

                    JSONObject obj = new JSONObject(response);
                    if(obj.optString("status").equals("true")){

                        DisposisiList = new ArrayList<>();
                        JSONArray dataArray  = obj.getJSONArray("data");

                        for (int i = 0; i < dataArray.length(); i++) {

                            DataDisposisi playerModel = new DataDisposisi();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            playerModel.setId_disposisi(dataobj.getString("id_disposisi"));
                            playerModel.setNo_surat(dataobj.getString("no_surat"));
                            playerModel.setAsal(dataobj.getString("asal"));
                            playerModel.setTgl_disposisi(dataobj.getString("tgl_disposisi"));
                            playerModel.setId_surat(dataobj.getString("id_surat"));
                            playerModel.setPerihal(dataobj.getString("perihal"));
                            playerModel.setIsi_disposisi(dataobj.getString("isi_disposisi"));
                            playerModel.setLampiran(dataobj.getString("lampiran"));
                            playerModel.setDerajat_surat(dataobj.getString("derajat_surat"));
                            playerModel.setNo_agenda(dataobj.getString("no_agenda"));
                            playerModel.setJenis_naskah_dinas(dataobj.getString("jenis_naskah_dinas"));
                            playerModel.setTersier(dataobj.getString("tersier"));
                            playerModel.setNama(dataobj.getString("nama"));
                            playerModel.setNama_asal(dataobj.getString("nama_asal"));
                            playerModel.setStatus_disposisi(dataobj.getString("status_disposisi"));
                            playerModel.setStatus_buat(dataobj.getString("status_buat"));
                            playerModel.setTujuan(dataobj.getString("tujuan"));

                            DisposisiList.add(playerModel);

                        }

                        setupRecycler();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(DetailSuratMasukActivityActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_surat", String.valueOf(idx));

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }


    private void setupRecycler(){
        recyclerView.setAdapter(new DisposisiSuratAdapter(this, DisposisiList));
        DisposisiSuratAdapter.notifyDataSetChanged();

//        rvAdapter = new AdapterJasa(this,dataModelArrayList);
//        recyclerView.setAdapter(rvAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
