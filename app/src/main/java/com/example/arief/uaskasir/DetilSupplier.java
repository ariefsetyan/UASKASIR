package com.example.arief.uaskasir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetilSupplier extends AppCompatActivity {
    private String url = Server.URL + "edit.php";
    private String url_delete = Server.URL + "delateKar.php";
    Toolbar toolbar;
    List<Supplier> sampelsupplier;
    AppCompatEditText nama, alamat, nomer;
    Button simpan,hapus;
    public final static String TAG_ID = "id";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_NOMER = "tlp";
    private static final String TAG = DetilSupplier.class.getSimpleName();
    int success;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_supplier);
        nama = (AppCompatEditText)findViewById(R.id.nama);
        alamat = (AppCompatEditText)findViewById(R.id.alamat);
        nomer = (AppCompatEditText)findViewById(R.id.nomer);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String idx = RecycleAdapter.ids;
        Log.d("kunci", "onCreate: "+idx);

        edit(idx);

        hapus = (Button)findViewById(R.id.hapus);
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String idx = RecycleAdapter.ids;
                Log.d("kunci", "onCreate: "+idx);
                delete(idx);
            }
        });
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
                        String idx      = jObj.getString(TAG_ID);
                        String namax    = jObj.getString(TAG_NAMA);
                        String alamatx  = jObj.getString(TAG_ALAMAT);
                        String telpx  = jObj.getString(TAG_NOMER);

                        Log.e("idsupplier", "onResponse: "+idx);
                        Log.e("nama", "onResponse: "+namax);

                        nama.setText(namax);
                        alamat.setText(alamatx);
                        nomer.setText(telpx);

                    } else {
                        Toast.makeText(DetilSupplier.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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

                        Toast.makeText(DetilSupplier.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();


                    } else {
                        Toast.makeText(DetilSupplier.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
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
                Toast.makeText(DetilSupplier.this, error.getMessage(), Toast.LENGTH_LONG).show();
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
