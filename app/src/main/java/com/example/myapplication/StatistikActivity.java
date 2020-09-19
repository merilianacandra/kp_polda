package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StatistikActivity extends AppCompatActivity {

    String id, monthstr, username;
    int success;
    int monthat, yearat, tujuan, month, month_pilihan, tahun_pilihan;
    String tag_json_obj = "json_obj_req";
    private static final String TAG = StatistikActivity.class.getSimpleName();
    SharedPreferences sharedpreferences;
    TextView txt_bulan, txt_tahun;
    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    private static String url_select_jumlah_surat_masuk 	 = Server.URL + "select_jumlah_surat_masuk.php";
    private static String url_select_jumlah_surat_keluar 	 = Server.URL + "select_jumlah_surat_keluar.php";
    private static String url_select_jumlah_disposisi_masuk 	 = Server.URL + "select_jumlah_disposisi_masuk.php";
    private static String url_select_jumlah_disposisi_keluar 	 = Server.URL + "select_jumlah_disposisi_keluar.php";

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private Button btDatePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID,"");
        username = sharedpreferences.getString(TAG_USERNAME,"");
        tujuan = Integer.parseInt(id);
        GregorianCalendar gc = new GregorianCalendar();
        yearat = gc.get(Calendar.YEAR);
        month = gc.get(Calendar.MONTH);
        monthat =month+1;

        if (monthat == 1){
            monthstr ="Januari";
        }  else if (monthat == 2){
            monthstr ="Februari";
        } else if (monthat == 3){
            monthstr ="Maret";
        } else if (monthat == 4){
            monthstr ="April";
        } else if (monthat == 5){
            monthstr ="Mei";
        } else if (monthat == 6){
            monthstr ="Juni";
        } else if (monthat == 7){
            monthstr ="Juli";
        } else if (monthat == 8){
            monthstr ="Agustus";
        } else if (monthat == 9){
            monthstr ="September";
        } else if (monthat == 10){
            monthstr ="Oktober";
        } else if (monthat == 11){
            monthstr ="November";
        } else {
            monthstr ="Desember";
        }


        txt_bulan = (TextView) findViewById(R.id.bulan);
        txt_tahun = (TextView) findViewById(R.id.tahun);

        txt_bulan.setText(monthstr);
        txt_tahun.setText(Integer.toString(yearat));
        select_jumlah_surat_masuk(monthat,yearat);
        select_jumlah_surat_keluar(monthat,yearat);
        select_jumlah_disposisi_masuk(tujuan,monthat,yearat);
        select_jumlah_disposisi_keluar(Integer.parseInt(id),monthat,yearat);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

//        btDatePicker = (Button) findViewById(R.id.bt_datepicker);
//        btDatePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDateDialog();
//            }
//        });

    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                month_pilihan = monthOfYear;
                tahun_pilihan = year;
                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
//                tvDateResult.setText(dateFormatter.format(newDate.getTime()));
                txt_bulan.setText(month_pilihan);
                txt_tahun.setText(tahun_pilihan);
                select_jumlah_surat_masuk(monthat,yearat);
                select_jumlah_surat_keluar(monthat,yearat);
                select_jumlah_disposisi_masuk(tujuan,monthat,yearat);
                select_jumlah_disposisi_keluar(Integer.parseInt(id),monthat,yearat);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    private void select_jumlah_surat_masuk( final int bulanx, final int tahunx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_select_jumlah_surat_masuk, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt("success");

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get select data", jObj.toString());
                        String jumlah    = jObj.getString("jumlah_surat_masuk");
//                        String idx    = jObj.getString("id_transaksi");
                        final TextView tvTotal = findViewById(R.id.jumlah_surat_masuk);


                        tvTotal.setText(jumlah);
//                        total = Integer.parseInt(totalx);

                    } else {
                        Toast.makeText(StatistikActivity.this, jObj.getString("message"), Toast.LENGTH_LONG).show();
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
                Toast.makeText(StatistikActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("bulan", String.valueOf(bulanx));
                params.put("tahun", String.valueOf(tahunx));

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void select_jumlah_surat_keluar(final int bulanx, final int tahunx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_select_jumlah_surat_keluar, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt("success");

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get select data", jObj.toString());
                        String jumlah_keluar    = jObj.getString("jumlah_surat_keluar");
//                        String idx    = jObj.getString("id_transaksi");
                        final TextView tvTotal_keluar = findViewById(R.id.jumlah_surat_keluar);


                        tvTotal_keluar.setText(jumlah_keluar);
//                        total = Integer.parseInt(totalx);

                    } else {
                        Toast.makeText(StatistikActivity.this, jObj.getString("message"), Toast.LENGTH_LONG).show();
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
                Toast.makeText(StatistikActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("bulan", String.valueOf(bulanx));
                params.put("tahun", String.valueOf(tahunx));

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void select_jumlah_disposisi_masuk(final int idx, final int bulanx, final int tahunx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_select_jumlah_disposisi_masuk, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt("success");

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get select data", jObj.toString());
                        String jumlah_disposisi_masuk    = jObj.getString("jumlah_disposisi_masuk");
//                        String idx    = jObj.getString("id_transaksi");
                        final TextView tvTotal_disposisi_masuk = findViewById(R.id.jumlah_disposisi_masuk);


                        tvTotal_disposisi_masuk.setText(jumlah_disposisi_masuk);
//                        total = Integer.parseInt(totalx);

                    } else {
                        Toast.makeText(StatistikActivity.this, jObj.getString("message"), Toast.LENGTH_LONG).show();
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
                Toast.makeText(StatistikActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("tujuan", String.valueOf(idx));
                params.put("bulan", String.valueOf(bulanx));
                params.put("tahun", String.valueOf(tahunx));

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void select_jumlah_disposisi_keluar(final int idx, final int bulanx, final int tahunx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_select_jumlah_disposisi_keluar, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt("success");

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get select data", jObj.toString());
                        String jumlah_disposisi_keluar    = jObj.getString("jumlah_disposisi_keluar");
//                        String idx    = jObj.getString("id_transaksi");
                        final TextView tvTotal_disposisi_keluar = findViewById(R.id.jumlah_disposisi_keluar);


                        tvTotal_disposisi_keluar.setText(jumlah_disposisi_keluar);
//                        total = Integer.parseInt(totalx);

                    } else {
                        Toast.makeText(StatistikActivity.this, jObj.getString("message"), Toast.LENGTH_LONG).show();
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
                Toast.makeText(StatistikActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("asal", String.valueOf(idx));
                params.put("bulan", String.valueOf(bulanx));
                params.put("tahun", String.valueOf(tahunx));

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}
