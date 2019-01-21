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
import com.example.arief.uaskasir.custom.recycleviewSupplier.RecycleAdapter;
import com.example.arief.uaskasir.custom.recycleviewSupplier.Supplier;
import com.example.arief.uaskasir.server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormBarangActivity extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatEditText nama, hargaBeli, hargaJual, stok;
    Spinner kdSupplier;
    Button simpan;
    public String idsuppliers;

    ProgressDialog pDialog;
    Intent intent;

    List<String> namaSupplier;
    List<String> idsupplier;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "insertbarang.php";
    private String urlsupplier = Server.URL + "selectsupplier.php";

    private static final String TAG = FormSupplierActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_NAMA = "nama_barang";
    public final static String TAG_HARGA_BELI = "harga_beli";
    public final static String TAG_HARGA_JUAL = "harga_jual";
    public final static String TAG_STOK = "stok";
    public final static String TAG_KD_SUPPLIER = "kd_supplier";

    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_barang);
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
        hargaBeli = (AppCompatEditText) findViewById(R.id.hargaBeli);
        hargaJual = (AppCompatEditText) findViewById(R.id.hargaJual);
        stok = (AppCompatEditText) findViewById(R.id.stok);

        getDataSupplier();

        idsupplier = new ArrayList<>();
        namaSupplier = new ArrayList<>();
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

        simpan = (Button)findViewById(R.id.simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nama = nama.getText().toString();
                String HargaBeli = hargaBeli.getText().toString();
                String HargaJual = hargaJual.getText().toString();
                String Stok = stok.getText().toString();
                String kodeSupplier = idsuppliers;
//                Log.e("kodesup",kodeSupplier);

                if (conMgr.getActiveNetworkInfo() != null
                        && conMgr.getActiveNetworkInfo().isAvailable()
                        && conMgr.getActiveNetworkInfo().isConnected()) {
                    checkKaryawan(Nama,HargaBeli,HargaJual,Stok,kodeSupplier);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkKaryawan(final String Nama, final String HargaBeli,final String HargaJual, final String Stok,final String kodeSupplier) {

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Simpan Response: " + response.toString());
//                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully simpan!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        nama.setText("");
                        hargaBeli.setText("");
                        hargaJual.setText("");
                        stok.setText("");
//                        kdSupplier.setText("");

                        intent = new Intent(FormBarangActivity.this, BarangActivity.class);
                        finish();
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

//                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put(TAG_NAMA, Nama);
                params.put(TAG_HARGA_BELI, HargaBeli);
                params.put(TAG_HARGA_JUAL, HargaJual);
                params.put(TAG_STOK, Stok);
                params.put(TAG_KD_SUPPLIER, kodeSupplier);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(FormBarangActivity.this, SupplierActivity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    //spinner supplier
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

                    kdSupplier.setAdapter(new ArrayAdapter<String>(FormBarangActivity.this,android.R.layout.simple_spinner_dropdown_item,namaSupplier));


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
}
