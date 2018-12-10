package com.example.arief.uaskasir.custom.recycleviewSupplier;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.arief.uaskasir.DetilSupplier;
import com.example.arief.uaskasir.R;
import com.example.arief.uaskasir.custom.RecycleviewKaryawan.Karyawan;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Supplier> suppliers;
    private Context context;
    public static String ids;
    public static Intent intent;
//    AlertDialog.Builder dialog;
//    List<Supplier> itemList = new ArrayList<Supplier>();

    public RecycleAdapter(Context context, List<Supplier> suppliers) {
        this.suppliers = suppliers;
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
        Supplier samplesupplier = suppliers.get(i);
        ids = String.valueOf(samplesupplier.id);
        viewHolder.nama.setText(samplesupplier.nama);
        viewHolder.nomer.setText(samplesupplier.nomer);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "item"+ ids, Toast.LENGTH_SHORT).show();
//                intent = new Intent(context,DetilSupplier.class);
//                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }
}
