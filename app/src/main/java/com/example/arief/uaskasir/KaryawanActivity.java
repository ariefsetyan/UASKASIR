package com.example.arief.uaskasir;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.arief.uaskasir.custom.RecycleviewKaryawan.Karyawan;
import com.example.arief.uaskasir.custom.RecycleviewKaryawan.RecycleAdapter;
import com.example.arief.uaskasir.server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class KaryawanActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button addKaryawan;
    RecyclerView recyclerView;
    AlertDialog.Builder dialog;

    List<Karyawan> samplekaryawan;

    private String url = Server.URL + "selectkaryawan.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karyawan);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addKaryawan = findViewById(R.id.addkaryawan);
        addKaryawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KaryawanActivity.this,FormKaryawanAktivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycle_id);
        samplekaryawan = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        getData();

    }

    public void getData(){
        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("datas");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject karyawan = array.getJSONObject(i);
                        samplekaryawan.add(new Karyawan(
                                karyawan.getInt("id"),
                                karyawan.getString("nama"),
                                karyawan.getString("alamat"),
                                karyawan.getString("email"),
                                karyawan.getString("nomer")
                        ));
                    }

                    RecycleAdapter adapter = new RecycleAdapter(KaryawanActivity.this, samplekaryawan);
                    recyclerView.setAdapter(adapter);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
