package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.DataJenisNaskahDinas;
import com.example.myapplication.data.DataKlasifikasiSurat;
import com.example.myapplication.data.DataKodeArsip;
import com.example.myapplication.data.DataPegawai;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class KodeArsipAdapter extends RecyclerView.Adapter<KodeArsipAdapter.MyViewHolder> {

    List<DataKodeArsip> my_list;
    List<DataKodeArsip> my_listfull;
    Context context;

    public KodeArsipAdapter(Context context, List<DataKodeArsip> my_list) {
        this.my_list = my_list;
        my_listfull = new ArrayList<>(my_list);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_kode_arsip,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DataKodeArsip model=my_list.get(position);
//        Picasso.get().load(model.getImage()).into(holder.imagek);
        holder.tersierk.setText(model.getTersier());
        holder.keterangank.setText(model.getKeterangan());

//        holder.relative.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context, JasaDetails.class);
//                intent.putExtra("image", model.getImage());
//                intent.putExtra("name", model.getNama());
//                intent.putExtra("buka",model.getBuka());
//                intent.putExtra("hp",model.getHp());
//                intent.putExtra("alamat",model.getAlamat());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        //        ImageView imagek;
        TextView tersierk,keterangank ;
//        RelativeLayout relative;

        public MyViewHolder(View itemView) {
            super(itemView);

//            imagek=itemView.findViewById(R.id.images);
            tersierk= itemView.findViewById(R.id.kode_arsip);
            keterangank= itemView.findViewById(R.id.nama_kode_arsip);

//            relative=itemView.findViewById(R.id.relatives);
        }
    }


    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataKodeArsip> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(my_listfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (DataKodeArsip item : my_listfull) {
                    if (item.getTersier().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getKeterangan().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            my_list.clear();
            my_list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
