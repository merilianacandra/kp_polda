package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.adapter.LevelAdapter;
import com.example.myapplication.adapter.LevelPegawaiAdapter;
import com.example.myapplication.app.AppController;
import com.example.myapplication.data.DataLevel;
import com.example.myapplication.data.DataLevelPegawai;
import com.example.myapplication.util.Server;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormDisposisi2Activity extends AppCompatActivity {

    TextView tno_surat, tperihal, tderajat_surat, tno_agenda, tisi_disposisi;
    EditText isi_disposisi;
    String id_surat, pno_surat, pperihal, pderajat_surat, pno_agenda, pisi_disposisi, id_disposisi;
    String id, id_pegawai, username, isi,id_pegawaix, id_sub_bagian;
    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_SUB_BAGIAN = "id_sub_bagian";
    int success;
    ListView list;
    TextView txt_menu;
    String dipilih, dipilihx;
    private static final String TAG = FormDisposisiActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";
    LevelAdapter adapter;
    LevelPegawaiAdapter adapterPegawai;
    ProgressDialog pDialog;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    List<DataLevel> itemList = new ArrayList<DataLevel>();
    List<DataLevelPegawai> itemListPegawai = new ArrayList<DataLevelPegawai>();
    ArrayList<DataLevel> level = new ArrayList<DataLevel>();

    // Sesuaikan dengan IP Address PC/LAptop atau ip emulator bawaan android 10.0.2.2
    private static String url = Server.URL + "checkbox_pegawai.php";
    private static String url_pegawai = Server.URL + "checkbox_pegawai.php";
    private static String url_select_id_pegawai	 = Server.URL + "select_id_pegawai.php";
    private static String url_insert 	 = Server.URL + "insert_disposisi.php";
    private static String url_update_buat_disposisi 	 = Server.URL + "update_buat_disposisi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_disposisi2);

        sharedpreferences = this.getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        id = sharedpreferences.getString(TAG_ID, "");
        username = sharedpreferences.getString(TAG_USERNAME,"");
        id_sub_bagian = sharedpreferences.getString(TAG_SUB_BAGIAN,"");

        tno_surat = (TextView) findViewById(R.id.form_nomorsurat);
        tno_agenda = (TextView) findViewById(R.id.form_nomoragenda);
        tperihal = (TextView) findViewById(R.id.form_perihal);
        tderajat_surat = (TextView) findViewById(R.id.form_kategori);
        tisi_disposisi = (TextView) findViewById(R.id.isi_disposisi_sebelum);

        id_disposisi = getIntent().getStringExtra("id_disposisi");
        id_surat = getIntent().getStringExtra("id_surat");
        pno_surat = getIntent().getStringExtra("no_surat");
        pno_agenda = getIntent().getStringExtra("no_agenda");
        pperihal = getIntent().getStringExtra("perihal");
        pderajat_surat = getIntent().getStringExtra("derajat_surat");
        pisi_disposisi = getIntent().getStringExtra("isi_disposisi");

        tno_surat.setText(pno_surat);
        tno_agenda.setText(pno_agenda);
        tperihal.setText(pperihal);
        tderajat_surat.setText(pderajat_surat);
        tisi_disposisi.setText(pisi_disposisi);

        list = (ListView) findViewById(R.id.list_level);
        isi_disposisi = (EditText) findViewById(R.id.isi_disposisi);



        callVolley();

        adapter =new LevelAdapter(this,(ArrayList<DataLevel>) itemList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {

            @Override
            public void onItemClick (AdapterView < ? > adapterView, View view, int position, long l){
                adapter.setCheckBox(position);
            }
        });
    }

    private void callVolley(){
        itemList.clear();
        // menapilkan dialog loading
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        showDialog();

        // membuat request JSON
        JsonObjectRequest jArr = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                hideDialog();

                // Parsing json

                try {
                    for (int i = 0; i < response.getJSONArray("data").length(); i++) {
                        JSONObject obj = response.getJSONArray("data").getJSONObject(i);

                        DataLevel item = new DataLevel();

                        item.setLevel(obj.getString("nama"));
                        item.setJabatan(obj.getString("jabatan_pegawai"));
                        item.setId_pegawai(obj.getString("id_pegawai"));

                        // menambah item ke array
                        itemList.add(item);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.DEBUG = true;
                VolleyLog.d(TAG, "Error: " + error.toString());
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideDialog();

                if(error instanceof NoConnectionError){
                    ConnectivityManager cm = (ConnectivityManager)getApplicationContext()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = null;
                    if (cm != null) {
                        activeNetwork = cm.getActiveNetworkInfo();
                    }
                    if(activeNetwork != null && activeNetwork.isConnectedOrConnecting()){
                        Toast.makeText(FormDisposisi2Activity.this, "Server is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FormDisposisi2Activity.this, "Your device is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    }
                } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
                        || (error.getCause() != null &&  error.getCause().getMessage() != null
                        && error.getCause().getMessage().contains("connection"))){
                    Toast.makeText(FormDisposisi2Activity.this, "Your device is not connected to internet.",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof MalformedURLException){
                    Toast.makeText(FormDisposisi2Activity.this, "Bad Request.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
                        || error.getCause() instanceof JSONException
                        || error.getCause() instanceof XmlPullParserException){
                    Toast.makeText(FormDisposisi2Activity.this, "Parse Error (because of invalid json or xml).",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof OutOfMemoryError){
                    Toast.makeText(FormDisposisi2Activity.this, "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
                }else if (error instanceof AuthFailureError){
                    Toast.makeText(FormDisposisi2Activity.this, "server couldn't find the authenticated request.",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
                    Toast.makeText(FormDisposisi2Activity.this, "Server is not responding.", Toast.LENGTH_SHORT).show();
                }else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
                        || error.getCause() instanceof ConnectTimeoutException
                        || error.getCause() instanceof SocketException
                        || (error.getCause().getMessage() != null
                        && error.getCause().getMessage().contains("Connection timed out"))) {
                    Toast.makeText(FormDisposisi2Activity.this, "Connection timeout error",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FormDisposisi2Activity.this, "An unknown error occurred.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        })
//        {
//
//            @Override
//            protected Map<String, String> getParams() {
//                // Posting parameters to login url
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("id_sub_bagian", id_sub_bagian);
//
//
//                return params;
//            }
//
//        }
        ;

        // menambah request ke request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void simpan_disposisi(View view) {
        String checkbox = "";
//        ArrayList<DataLevel> level = new ArrayList<DataLevel>();
//        int totalChecked = 0;
//        for(DataLevel data : adapter.getAllData()){
//            totalChecked += data.isCheckbox()? 1 : 0;
//        }

        for (DataLevel hold : adapter.getAllData()) {
            if (hold.isCheckbox()) {
                checkbox += hold.getId_pegawai();
                level.add(hold);
            }
        }

//        totalChecked = adapter.getAllData().stream().filter(s -> s.isCheckbox()).count();
        if (!checkbox.isEmpty()) {
            dipilihx = checkbox;

        } else {
            dipilihx = "Anda Belum Memilih Menu.";
        }
//        select_id_pegawai(dipilih);
//        id_pegawaix = id_pegawai;
        isi = String.valueOf(isi_disposisi.getText());
        simpan_update();
        update_buat_disposisi();
        Intent intent = new Intent(FormDisposisi2Activity.this, MainActivity.class);
        finish();
        startActivity(intent);

    }


    public void pilih_level(View view) {
        String checkbox = "";
//        ArrayList<DataLevel> level = new ArrayList<DataLevel>();
//        int totalChecked = 0;
//        for(DataLevel data : adapter.getAllData()){
//            totalChecked += data.isCheckbox()? 1 : 0;
//        }

        for (DataLevel hold : adapter.getAllData()) {
            if (hold.isCheckbox()) {
                checkbox += hold.getId_pegawai();
                level.add(hold);
            }
        }

//        totalChecked = adapter.getAllData().stream().filter(s -> s.isCheckbox()).count();
        if (!checkbox.isEmpty()) {
            dipilih = checkbox;

        } else {
            dipilih = "Anda Belum Memilih Menu.";
        }
//        select_id_pegawai(dipilih);
//        id_pegawaix = id_pegawai;
        isi_disposisi.setText(dipilih);
//        callVolleyPegawai(dipilih);

//        adapterPegawai = new LevelPegawaiAdapter(this, (ArrayList<DataLevelPegawai>) itemListPegawai);
//        list.setAdapter(adapterPegawai);
//
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                adapterPegawai.setCheckBox(position);
//            }
//        });

    }

    private void simpan_update() {
//        String url;
//        // jika id kosong maka simpan, jika id ada nilainya maka update
//        if (id.isEmpty()){
//            url = url_insert;
//        } else {
//            url = url_update;
//        }

        StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("insert disposisi", jObj.toString());

//                        callVolley();
//                        kosong();
//
                        Toast.makeText(FormDisposisi2Activity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(FormDisposisi2Activity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(FormDisposisi2Activity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                params.put("id_surat", String.valueOf(id_surat));
                params.put("tujuan", String.valueOf(dipilihx));
                params.put("asal", String.valueOf(id));
                params.put("no_agenda", String.valueOf(pno_agenda));
                params.put("isi_disposisi", String.valueOf(isi));


                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void update_buat_disposisi() {
//        String url;
//        // jika id kosong maka simpan, jika id ada nilainya maka update
//        if (id.isEmpty()){
//            url = url_insert;
//        } else {
//            url = url_update;
//        }

        StringRequest strReq = new StringRequest(Request.Method.POST, url_update_buat_disposisi, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("update status_buat", jObj.toString());

////                        callVolley();
////                        kosong();
////
                        Toast.makeText(FormDisposisi2Activity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
////                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(FormDisposisi2Activity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(FormDisposisi2Activity.this, error.getMessage(), Toast.LENGTH_LONG).show();
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