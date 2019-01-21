package com.example.arief.uaskasir;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.arief.uaskasir.app.AppController;
import com.example.arief.uaskasir.custom.RecycleviewKaryawan.Karyawan;
import com.example.arief.uaskasir.custom.RecycleviewKaryawan.RecycleAdapter;
import com.example.arief.uaskasir.server.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditKaryawan extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatEditText nama, alamat, email, nomer;
    Button simpan, hapus;

    ProgressDialog pDialog;
    Intent intent;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "editkaryawan.php";
    private String urlupdate = Server.URL + "updateKar.php";
    private String url_delete = Server.URL + "delateKar.php";

    private static final String TAG = FormSupplierActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_ID = "id";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_TLP = "tlp";
//    String id, nama, alamat;

    String tag_json_obj = "json_obj_req";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_karyawan);

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama = (AppCompatEditText) findViewById(R.id.nama);
        alamat = (AppCompatEditText) findViewById(R.id.alamat);
        email = (AppCompatEditText) findViewById(R.id.email);
        nomer = (AppCompatEditText) findViewById(R.id.nomer);
        simpan = (Button) findViewById(R.id.simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                simpan_update();
            }
        });
        hapus = (Button) findViewById(R.id.hapus);
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String idx = RecycleAdapter.ids;
                Log.e("idkar", "onCreate: "+idx );
                delete(idx);
                Intent intent = new Intent(EditKaryawan.this,Karyawan.class);
                startActivity(intent);
            }
        });

        final String idx = RecycleAdapter.ids;
        Log.e("idkar", "onCreate: "+idx );
        edit(idx);
    }

    private void edit(final String idx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get edit data", jObj.toString());
                        String idAS      = jObj.getString(TAG_ID);
                        String namaAS    = jObj.getString(TAG_NAMA);
                        String alamatAS  = jObj.getString(TAG_ALAMAT);
                        String emailAS  = jObj.getString(TAG_EMAIL);
                        String telpAS  = jObj.getString(TAG_TLP);

                        Log.e("idsupplier", "onResponse: "+idx);
                        Log.e("nama", "onResponse: "+namaAS);

                        nama.setText(namaAS);
                        alamat.setText(alamatAS);
                        email.setText(emailAS);
                        nomer.setText(telpAS);

                    } else {
                        Toast.makeText(EditKaryawan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
//                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", idx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

//    private void simpan_update() {
//        String url;
//        // jika id kosong maka simpan, jika id ada nilainya maka update
////        if (id.isEmpty()){
////            url = url_insert;
////        } else {
////            url = url_update;
////        }
//
//        StringRequest strReq = new StringRequest(Request.Method.POST, urlupdate, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "Response: " + response.toString());
//
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    success = jObj.getInt(TAG_SUCCESS);
//
//                    // Cek error node pada json
//                    if (success == 1) {
//                        Log.e("Add/update", jObj.toString());
//
////                        callVolley();
////                        kosong();
//
//                        Toast.makeText(EditKaryawan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
////                        adapter.notifyDataSetChanged();
//
//                    } else {
////                        Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    // JSON error
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Error: " + error.getMessage());
////                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() {
//                // Posting parameters ke post url
//                Map<String, String> params = new HashMap<String, String>();
//                // jika id kosong maka simpan, jika id ada nilainya maka update
//                if (id.isEmpty()){
//                    params.put("nama", nama);
//                    params.put("alamat", alamat);
//                } else {
//                    params.put("id", id);
//                    params.put("nama", nama);
//                    params.put("alamat", alamat);
//                }
//
//                return params;
//            }
//
//        };
//
//        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
//    }

    private void delete(final String idx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_delete, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("delete", jObj.toString());

                        Toast.makeText(EditKaryawan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();


                    } else {
                        Toast.makeText(EditKaryawan.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(EditKaryawan.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", idx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
