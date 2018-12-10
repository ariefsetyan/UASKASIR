package com.example.arief.uaskasir;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.arief.uaskasir.custom.CustomAdapterGridView;

public class HomeActivity extends AppCompatActivity {
    GridView androidGridView;

    String CustomGridViewActivity[] = {"Mater Barang",
            "Transaksi",
            "Laporan",

    };
    int gridViewImageId[] = {
            R.drawable.ic_storage_black_24dp,
            R.drawable.ic_shopping_cart_black_24dp,
            R.drawable.ic_assessment_black_24dp
    };

    Toolbar toolbar;
    String id, username;
    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        androidGridView = (GridView)findViewById(R.id.gridView);
        CustomAdapterGridView customAdapterGridView = new CustomAdapterGridView(HomeActivity.this, CustomGridViewActivity,gridViewImageId);
        androidGridView.setAdapter(customAdapterGridView);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (CustomGridViewActivity[i] == "Mater Barang"){
                    Intent master = new Intent(HomeActivity.this,MasterActivity.class);
                    startActivity(master);
                }else if (CustomGridViewActivity[i] == "Transaksi"){
                    Toast.makeText(HomeActivity.this, "tran", Toast.LENGTH_SHORT).show();
//                    Intent Itransaksi=new Intent(Home.this,HomeTransaksi.class);
//                    startActivity(Itransaksi);
                }else if (CustomGridViewActivity[i] == "Laporan"){
                    Toast.makeText(HomeActivity.this, "laporan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(LoginActivity.session_status, false);
        editor.putString(TAG_ID, null);
        editor.putString(TAG_USERNAME, null);
        editor.commit();

        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        finish();
        startActivity(intent);
        Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}
