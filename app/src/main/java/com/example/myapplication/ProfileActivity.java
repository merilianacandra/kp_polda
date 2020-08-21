package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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

public class ProfileActivity extends AppCompatActivity {
    Button btn_logout;
    TextView txt_username;
    String id, username;
    SharedPreferences sharedpreferences;

    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    private static final String TAG = ProfileActivity.class.getSimpleName();
    int success;

    private static String url_select_user 	 = Server.URL + "select_profil.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btn_logout = (Button) findViewById(R.id.btn_logout);

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(TAG_ID,"");
        select_customer(Integer.parseInt(id));

        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_USERNAME, null);
                editor.commit();

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

    private void select_customer(final int idx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_select_user, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get select data", jObj.toString());
                        String idx    = jObj.getString("id_pegawai");
                        String namax  = jObj.getString("nama");
                        String nrpx  = jObj.getString("nrp");
                        String no_hpx  = jObj.getString("no_hp");
                        String alamatx = jObj.getString("alamat");
                        String pangkatx  = jObj.getString("pangkat");
                        String jabatanx  = jObj.getString("jabatan");
                        String tanggal_lahirx  = jObj.getString("tgl_lahir");



                        final TextView txt_nama = findViewById(R.id.nama);
                        final TextView txt_nrp = findViewById(R.id.nrp);
                        final TextView txt_no_hp = findViewById(R.id.phone);
                        final TextView txt_alamat = findViewById(R.id.alamat);
                        final TextView txt_tgl_lahir = findViewById(R.id.tgl_lahir);
                        final TextView txt_pangkat = findViewById(R.id.pangkat);
                        final TextView txt_jabatan = findViewById(R.id.jabatan);

                        txt_nama.setText(namax);
                        txt_no_hp.setText(no_hpx);
                        txt_alamat.setText(alamatx);
                        txt_nrp.setText(nrpx);
                        txt_tgl_lahir.setText(tanggal_lahirx);
                        txt_pangkat.setText(pangkatx);
                        txt_jabatan.setText(jabatanx);

                    } else {
                        Toast.makeText(ProfileActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id_pegawai", String.valueOf(idx));

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}
