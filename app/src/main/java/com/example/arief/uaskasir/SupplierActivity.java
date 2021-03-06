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
import com.example.arief.uaskasir.custom.recycleviewSupplier.RecycleAdapter;
import com.example.arief.uaskasir.custom.recycleviewSupplier.Supplier;
import com.example.arief.uaskasir.server.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SupplierActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button addSuppleir;
    RecyclerView recyclerView;
    AlertDialog.Builder dialog;

    List<Supplier> sampelsupplier;

    private String url = Server.URL + "selectsupplier.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addSuppleir = findViewById(R.id.addsupplier);
        addSuppleir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierActivity.this,FormSupplierActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recycle_id);
        sampelsupplier = new ArrayList<>();

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
                        JSONObject supplier = array.getJSONObject(i);
                        sampelsupplier.add(new Supplier(
                                supplier.getInt("id_supplier"),
                                supplier.getString("nama_supplier"),
                                supplier.getString("alamat"),
                                supplier.getString("telp")
                        ));
                    }

                    RecycleAdapter adapter = new RecycleAdapter(SupplierActivity.this, sampelsupplier);
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
