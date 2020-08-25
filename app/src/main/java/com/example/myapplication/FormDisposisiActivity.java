package com.example.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myapplication.adapter.LevelAdapter;
import com.example.myapplication.app.AppController;
import com.example.myapplication.data.DataLevel;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class FormDisposisiActivity extends AppCompatActivity {
    String[] pendidikan = {"PILIHAN", "Kasubag Renmin", "Kasubag Tekkom", "Kasubag Tekinfo"};
    TextView tno_surat, tperihal, tderajat_surat, tno_agenda;
    String id_surat, pno_surat, pperihal, pderajat_surat, pno_agenda;
    String id;
    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id";
    int success;

    ListView list;
    TextView txt_menu;
    String dipilih;
    private static final String TAG = FormDisposisiActivity.class.getSimpleName();

    LevelAdapter adapter;
    ProgressDialog pDialog;

    List<DataLevel> itemList = new ArrayList<DataLevel>();

    // Sesuaikan dengan IP Address PC/LAptop atau ip emulator bawaan android 10.0.2.2
    private static String url = "https://siapbali.000webhostapp.com/php_siapbali/checkbox_level.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_disposisi);

        sharedpreferences = this.getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        id = sharedpreferences.getString(TAG_ID, "");

        tno_surat = (TextView) findViewById(R.id.form_nomorsurat);
        tno_agenda = (TextView) findViewById(R.id.form_nomoragenda);
        tperihal = (TextView) findViewById(R.id.form_perihal);
        tderajat_surat = (TextView) findViewById(R.id.form_kategori);

        id_surat = getIntent().getStringExtra("id_surat");
        pno_surat = getIntent().getStringExtra("no_surat");
        pno_agenda = getIntent().getStringExtra("no_agenda");
        pperihal = getIntent().getStringExtra("perihal");
        pderajat_surat = getIntent().getStringExtra("derajat_surat");

        tno_surat.setText(pno_surat);
        tno_agenda.setText(pno_agenda);
        tperihal.setText(pperihal);
        tderajat_surat.setText(pderajat_surat);

        list = (ListView) findViewById(R.id.list_level);



//        formSubmit(dipilih);

    callVolley();

    adapter =new LevelAdapter(this,(ArrayList<DataLevel>) itemList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()

    {

        @Override
        public void onItemClick (AdapterView < ? > adapterView, View view,int position, long l){
        adapter.setCheckBox(position);
    }
    });


//        Spinner spinner = findViewById(R.id.spinner);
//        spinner.setOnItemSelectedListener(this);
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pendidikan);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(arrayAdapter);
    }

//    private void formSubmit(String hasil){
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//
//        txt_menu = (TextView) dialogView.findViewById(R.id.txt_menu);
//
//        txt_menu.setText(hasil);
//
//        dialog.setNeutralButton("CLOSE", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }

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

                            item.setLevel(obj.getString("level"));

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
                        Toast.makeText(FormDisposisiActivity.this, "Server is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FormDisposisiActivity.this, "Your device is not connected to internet.",
                                Toast.LENGTH_SHORT).show();
                    }
                } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
                        || (error.getCause() != null &&  error.getCause().getMessage() != null
                        && error.getCause().getMessage().contains("connection"))){
                    Toast.makeText(FormDisposisiActivity.this, "Your device is not connected to internet.",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof MalformedURLException){
                    Toast.makeText(FormDisposisiActivity.this, "Bad Request.", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
                        || error.getCause() instanceof JSONException
                        || error.getCause() instanceof XmlPullParserException){
                    Toast.makeText(FormDisposisiActivity.this, "Parse Error (because of invalid json or xml).",
                            Toast.LENGTH_SHORT).show();
                } else if (error.getCause() instanceof OutOfMemoryError){
                    Toast.makeText(FormDisposisiActivity.this, "Out Of Memory Error.", Toast.LENGTH_SHORT).show();
                }else if (error instanceof AuthFailureError){
                    Toast.makeText(FormDisposisiActivity.this, "server couldn't find the authenticated request.",
                            Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError || error.getCause() instanceof ServerError) {
                    Toast.makeText(FormDisposisiActivity.this, "Server is not responding.", Toast.LENGTH_SHORT).show();
                }else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
                        || error.getCause() instanceof ConnectTimeoutException
                        || error.getCause() instanceof SocketException
                        || (error.getCause().getMessage() != null
                        && error.getCause().getMessage().contains("Connection timed out"))) {
                    Toast.makeText(FormDisposisiActivity.this, "Connection timeout error",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FormDisposisiActivity.this, "An unknown error occurred.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

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

//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//        Toast.makeText(getApplicationContext(), pendidikan[position], Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }

    public void simpan_disposisi(View view) {
//        Intent intent = new Intent(FormDisposisiActivity.this, MainActivity.class);
//        intent.putExtra("id_surat", id_surat);
//        intent.putExtra("no_agenda", pno_agenda);
//        intent.putExtra("no_surat", pno_surat);
//        intent.putExtra("derajat_surat", pderajat_surat);
//        intent.putExtra("perihal", pperihal);
//        startActivity(intent);

        String checkbox = "";
        for (DataLevel hold : adapter.getAllData()) {
            if (hold.isCheckbox()) {
                checkbox += "\n" + hold.getLevel();
            }
        }
        if (!checkbox.isEmpty()) {
            dipilih = checkbox;
        } else {
            dipilih = "A+nda Belum Memilih Menu.";
        }
    }



}
