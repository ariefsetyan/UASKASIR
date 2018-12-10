package com.example.arief.uaskasir.custom.recycleviewSupplier;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder>{
        private Context mCtx;
        private List<Supplier> supplier;

    public Adapter(Context mCtx, List<Supplier> productList) {
        this.mCtx = mCtx;
        this.supplier = productList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
