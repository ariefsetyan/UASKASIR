package com.example.arief.uaskasir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
    List<Supplier> sampelsupplier;
    AppCompatEditText nama, alamat, nomer;
    public final static String TAG_ID = "id";
    private static final String TAG = DetilSupplier.class.getSimpleName();
    int success;
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_supplier);
        nama = (AppCompatEditText)findViewById(R.id.nama);
        alamat = (AppCompatEditText)findViewById(R.id.alamat);
        nomer = (AppCompatEditText)findViewById(R.id.nomer);
        final String idx = RecycleAdapter.ids;
        Log.d("kunci", "onCreate: "+idx);
//        getData(idx);
    }
//    menampilkan data berdasarkan id
//    public void getData(final String idx){
//        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    success = jsonObject.getInt(TAG_SUCCESS);
//                    // Cek error node pada json
//                    if (success == 1) {
//                        Log.d("get edit data", jsonObject.toString());
//                        String idx      = jsonObject.getString(TAG_ID);
////                        String namax    = jsonObject.getString(TAG_NAMA);
////                        String alamatx  = jsonObject.getString(TAG_ALAMAT);
//
//
////                        adapter.notifyDataSetChanged();
//
//                    } else {
////                        Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//                    }
////                    JSONArray array = jsonObject.getJSONArray("datas");
////                    for (int i = 0; i < jsonObject.length(); i++){
////                        JSONObject supplier = array.getJSONObject(i);
////                        String id = supplier.getString("id_supplier");
////                        String namas = supplier.getString("nama_supplier");
////                        String alamats = supplier.getString("alamat");
////                        String telps = supplier.getString("telp");
//////                        sampelsupplier.add(new Supplier(
//////                                supplier.getInt("id_supplier"),
//////                                supplier.getString("nama_supplier"),
//////                                supplier.getString("alamat"),
//////                                supplier.getString("telp")
//////                        )
//////                        );
////                        nama.setText(namas);
////                        alamat.setText(alamats);
////                        nomer.setText(telps);
////                    }
//                    //creating adapter object and setting it to recyclerview
////                    RecycleAdapter adapter = new RecycleAdapter(SupplierActivity.this, sampelsupplier);
////                    recyclerView.setAdapter(adapter);
//
//
//                }catch (    JSONException e){
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Log.e(TAG, "Login Error: " + error.getMessage());
////                Toast.makeText(getApplicationContext(),
////                        error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        }
//        ){
//            @Override
//            protected Map<String, String> getParams() {
//                // Posting parameters to login url
//                Map<String, String> params = new HashMap<String, String>();
//                params.put(TAG_ID, idx);
////            params.put(TAG_ALAMAT, Alamat);
////            params.put(TAG_TLP, Nomer);
//
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(strReq);
//
//    }
}
