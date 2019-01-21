package com.example.arief.uaskasir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.arief.uaskasir.custom.CustomAdapterGridView;

public class MasterActivity extends AppCompatActivity {
    Toolbar toolbar;
    GridView gridView;
    String CustomGridViewActivity[]={"Barang",
            "Karyawan",
            "Supplier"};
    int gridViewImageId[]={R.drawable.ic_assignment_black_24dp,
            R.drawable.ic_assignment_ind_black_24dp,
            R.drawable.supplier};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gridView=(GridView)findViewById(R.id.gridView);
        CustomAdapterGridView customAdapterGridView = new CustomAdapterGridView(MasterActivity.this,CustomGridViewActivity,gridViewImageId);
        gridView.setAdapter(customAdapterGridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (CustomGridViewActivity[i] == "Barang"){
                    Intent barang = new Intent(MasterActivity.this,BarangActivity.class);
                    startActivity(barang);
                }else if (CustomGridViewActivity[i]=="Karyawan"){
                    Intent intentKaryawan = new Intent(MasterActivity.this,KaryawanActivity.class);
                    startActivity(intentKaryawan);
                }else  if (CustomGridViewActivity[i] == "Supplier"){
                    Intent intentSupplier = new Intent(MasterActivity.this,SupplierActivity.class);
                    startActivity(intentSupplier);
                }
            }
        });
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
