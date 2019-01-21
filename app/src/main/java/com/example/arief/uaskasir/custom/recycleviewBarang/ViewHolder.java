package com.example.arief.uaskasir.custom.recycleviewBarang;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arief.uaskasir.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView nama, harga;
    public LinearLayout linearLayout;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        nama = (TextView)itemView.findViewById(R.id.nama);
        harga = (TextView)itemView.findViewById(R.id.hargaJual);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.recycleid_item);
    }
}
