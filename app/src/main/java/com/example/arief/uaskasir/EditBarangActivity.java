package com.example.arief.uaskasir;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.arief.uaskasir.app.AppController;
import com.example.arief.uaskasir.custom.recycleviewBarang.Barang;
import com.example.arief.uaskasir.custom.recycleviewBarang.RecycleAdapter;
import com.example.arief.uaskasir.server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditBarangActivity extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatEditText nama, hargaBeli, hargaJual, stok;
    Spinner kdSupplier;
    Button update,delete;

    ConnectivityManager conMgr;

    private String url = Server.URL + "editbarang.php";
    private String urlsupplier = Server.URL + "selectsupplier.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    int success;
    String tag_json_obj = "json_obj_req";

    List<String> namaSupplier;
    List<String> idsupplier;
    public String idsuppliers;

    public final static String TAG_ID = "id";
    public final static String TAG_NAMA = "nama_barang";
    public final static String TAG_BELI = "harga_beli";
    public final static String TAG_JUAL = "harga_jual";
    public final static String TAG_STOK = "stok";
    private static final String TAG = EditBarangActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        nama = (AppCompatEditText) findViewById(R.id.nama);
        hargaBeli = (AppCompatEditText) findViewById(R.id.hargaBeli);
        hargaJual = (AppCompatEditText) findViewById(R.id.hargaJual);
        stok = (AppCompatEditText) findViewById(R.id.stok);
        kdSupplier = (Spinner) findViewById(R.id.spinner);
        kdSupplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String supplier = kdSupplier.getItemAtPosition(kdSupplier.getSelectedItemPosition()).toString();
                idsuppliers = idsupplier.get(i);
                Toast.makeText(getApplicationContext(),supplier +"id: "+idsuppliers,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        idsupplier = new ArrayList<>();
        namaSupplier = new ArrayList<>();

        final String idx = RecycleAdapter.idbarang;
        Log.e("idbarang", "onCreate: "+idx );
        edit(idx);
        getDataSupplier();

    }

    public void getDataSupplier(){
        StringRequest strReq = new StringRequest(Request.Method.GET, urlsupplier, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("datas");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject supplier = array.getJSONObject(i);
                        String dataSupplier = supplier.getString("nama_supplier");
                        String idSupplier = String.valueOf(supplier.getInt("id_supplier"));
                        idsupplier.add(idSupplier);
                        namaSupplier.add(dataSupplier);

                    }

                    kdSupplier.setAdapter(new ArrayAdapter<String>(EditBarangActivity.this,android.R.layout.simple_spinner_dropdown_item,namaSupplier));


                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);

    }

//    public void getData(){
//        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray array = jsonObject.getJSONArray("datas");
////                    Log.e("data",array);
//                    for (int i = 0; i < array.length(); i++){
//                        JSONObject barang = array.getJSONObject(i);
//                        String NamaBarang = barang.getString("nama_barang");
//                        String HargaBeli = barang.getString("harga_beli");
//                        String HargaJual = barang.getString("harga_jual");
//                        String Stok = barang.getString("stok");
//
//                        nama.setText(NamaBarang);
//                        hargaBeli.setText(HargaBeli);
//                        hargaJual.setText(HargaJual);
//                        stok.setText(Stok);
//
//                    }
////                    kdSupplier.setAdapter(new ArrayAdapter<String>(FormBarangActivity.this,android.R.layout.simple_spinner_dropdown_item,namaSupplier));
//
//
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }
//        );
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(strReq);
//
//    }

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
                        String beliAS  = jObj.getString(TAG_BELI);
                        String jualAS  = jObj.getString(TAG_JUAL);
                        String stokAS  = jObj.getString(TAG_STOK);

                        Log.e("idsupplier", "onResponse: "+idx);
                        Log.e("nama", "onResponse: "+namaAS);

                        nama.setText(namaAS);
                        hargaBeli.setText(beliAS);
                        hargaJual.setText(jualAS);
                        stok.setText(stokAS);

                    } else {
                        Toast.makeText(EditBarangActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
