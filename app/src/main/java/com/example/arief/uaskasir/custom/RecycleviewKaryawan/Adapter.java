package com.example.arief.uaskasir.custom.RecycleviewKaryawan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mCtx;
    private List<Karyawan> karyawans;

    public Adapter(Context mCtx, List<Karyawan> karyawans) {
        this.mCtx = mCtx;
        this.karyawans = karyawans;
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
