package com.example.arief.uaskasir.custom.recycleviewBarang;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.arief.uaskasir.BarangActivity;
import com.example.arief.uaskasir.EditBarangActivity;
import com.example.arief.uaskasir.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Barang> barangs;
    private Context context;
    public static String ids;
    public static String idbarang;
    public static String hargaj;
    public static Intent intent;

    public RecycleAdapter(Context context, List<Barang> samplebarang) {
        this.barangs = samplebarang;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item_b,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Barang samplebarang = barangs.get(i);
//        ids = String.valueOf(samplebarang.id);
        hargaj = String.valueOf(samplebarang.hargajual);
//        Log.e("harga",hargaj);
        viewHolder.nama.setText(samplebarang.nama);
        viewHolder.harga.setText(hargaj);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idbarang = String.valueOf(samplebarang.id);
                Toast.makeText(context, "id "+ idbarang, Toast.LENGTH_SHORT).show();
                intent = new Intent(context,EditBarangActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return barangs.size();
    }
}
