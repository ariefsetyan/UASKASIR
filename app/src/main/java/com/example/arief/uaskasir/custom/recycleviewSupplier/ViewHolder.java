package com.example.arief.uaskasir.custom.recycleviewSupplier;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.arief.uaskasir.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView nama, nomer;
    public LinearLayout linearLayout;
    public ViewHolder(@NonNull View itemView) {

        super(itemView);
        nama = (TextView)itemView.findViewById(R.id.nama);
        nomer = (TextView)itemView.findViewById(R.id.nomer);
        linearLayout = (LinearLayout)itemView.findViewById(R.id.recycleid_item);
    }
}