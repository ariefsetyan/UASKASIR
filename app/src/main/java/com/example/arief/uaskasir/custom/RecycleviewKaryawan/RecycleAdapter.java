package com.example.arief.uaskasir.custom.RecycleviewKaryawan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.arief.uaskasir.EditKaryawan;
import com.example.arief.uaskasir.R;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<ViewHolder>{
    private List<Karyawan> karyawans;
    private Context context;
    public static String ids;
    public static Intent intent;
//    AlertDialog.Builder dialog;
//    List<Supplier> itemList = new ArrayList<Supplier>();

    public RecycleAdapter(Context context, List<Karyawan> karyawans) {
        this.karyawans = karyawans;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Karyawan samplekaryawan = karyawans.get(i);
//        ids = String.valueOf(samplekaryawan.id);
        viewHolder.nama.setText(samplekaryawan.nama);
        viewHolder.nomer.setText(samplekaryawan.nomer);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ids = String.valueOf(samplekaryawan.id);
                Toast.makeText(context, "item "+ ids, Toast.LENGTH_SHORT).show();
                intent = new Intent(context,EditKaryawan.class);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return karyawans.size();
    }

}
