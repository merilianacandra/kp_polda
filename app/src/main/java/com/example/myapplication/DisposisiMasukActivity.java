package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.adapter.DisposisiMasukAdapter;
import com.example.myapplication.adapter.SuratMasukAdapter;
import com.example.myapplication.app.AppController;
import com.example.myapplication.data.DataDisposisi;
import com.example.myapplication.data.DataSurat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DisposisiMasukActivity extends AppCompatActivity {

    private static final String TAG = DisposisiMasukActivity.class.getSimpleName();
    private String URLstring = "https://siapbali.000webhostapp.com/php_siapbali/select_disposisi_masuk.php";
    private static ProgressDialog mProgressDialog;
    List<DataDisposisi> DisposisiList = new ArrayList<>();
    private com.example.myapplication.adapter.DisposisiMasukAdapter DisposisiMasukAdapter;
    private RecyclerView recyclerView;
    Context mContext;
    String id_level;
    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disposisi_masuk);

        recyclerView = findViewById(R.id.recyclerView1);

        mContext = this;

        DisposisiMasukAdapter = new DisposisiMasukAdapter(mContext, DisposisiList);
        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        id_level = getIntent().getStringExtra("id_level");
//        id_kat_produk ="2";



        select_disposisi(Integer.parseInt(id_level));
    }

//    public void det_disposisi_masuk(View view) {
//        Intent intent = new Intent(DisposisiMasukActivity.this, DetailDisposisiMasukActivity.class);
//        startActivity(intent);
//    }

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
                            playerModel.setFile_disposisi(dataobj.getString("file_disposisi"));
                            playerModel.setDerajat_surat(dataobj.getString("derajat_surat"));
                            playerModel.setNo_agenda(dataobj.getString("no_agenda"));
                            playerModel.setJenis_naskah_dinas(dataobj.getString("jenis_naskah_dinas"));
                            playerModel.setTersier(dataobj.getString("tersier"));
                            playerModel.setNama(dataobj.getString("nama"));

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
                Toast.makeText(DisposisiMasukActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_level", String.valueOf(idx));

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }


    private void setupRecycler(){
        recyclerView.setAdapter(new DisposisiMasukAdapter(this, DisposisiList));
        DisposisiMasukAdapter.notifyDataSetChanged();

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
