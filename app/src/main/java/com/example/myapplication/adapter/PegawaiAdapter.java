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
import com.example.myapplication.data.DataPegawai;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PegawaiAdapter extends RecyclerView.Adapter<PegawaiAdapter.MyViewHolder> {

    List<DataPegawai> my_list;
    List<DataPegawai> my_listfull;
    Context context;

    public PegawaiAdapter(Context context, List<DataPegawai> my_list) {
        this.my_list = my_list;
        my_listfull = new ArrayList<>(my_list);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pegawai,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DataPegawai model=my_list.get(position);
//        Picasso.get().load(model.getImage()).into(holder.imagek);
        holder.namak.setText(model.getNama());
        holder.nrpk.setText(model.getNrp());
        holder.pangkatk.setText(model.getPangkat());
        holder.jabatank.setText(model.getJabatan());
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
        TextView namak, nrpk, pangkatk, jabatank;
//        RelativeLayout relative;

        public MyViewHolder(View itemView) {
            super(itemView);

//            imagek=itemView.findViewById(R.id.images);
            namak= itemView.findViewById(R.id.nama);
            nrpk=itemView.findViewById(R.id.nrp);
            pangkatk=itemView.findViewById(R.id.pangkat);
            jabatank=itemView.findViewById(R.id.jabatan);
//            relative=itemView.findViewById(R.id.relatives);
        }
    }


    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataPegawai> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(my_listfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (DataPegawai item : my_listfull) {
                    if (item.getNama().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getJabatan().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getPangkat().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getNrp().toLowerCase().contains(filterPattern)) {
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
