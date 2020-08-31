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
import com.example.myapplication.data.DataDerajatSurat;
import com.example.myapplication.data.DataJenisNaskahDinas;
import com.example.myapplication.data.DataPegawai;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DerajatSuratAdapter extends RecyclerView.Adapter<DerajatSuratAdapter.MyViewHolder> {

    List<DataDerajatSurat> my_list;
    List<DataDerajatSurat> my_listfull;
    Context context;

    public DerajatSuratAdapter(Context context, List<DataDerajatSurat> my_list) {
        this.my_list = my_list;
        my_listfull = new ArrayList<>(my_list);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_derajat_surat,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DataDerajatSurat model=my_list.get(position);
//        Picasso.get().load(model.getImage()).into(holder.imagek);
        holder.nama_derajatk.setText(model.getDerajat_surat());

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
        TextView nama_derajatk ;
//        RelativeLayout relative;

        public MyViewHolder(View itemView) {
            super(itemView);

//            imagek=itemView.findViewById(R.id.images);
            nama_derajatk= itemView.findViewById(R.id.nama_derajat);

//            relative=itemView.findViewById(R.id.relatives);
        }
    }


    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DataDerajatSurat> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(my_listfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (DataDerajatSurat item : my_listfull) {
                    if (item.getDerajat_surat().toLowerCase().contains(filterPattern)) {
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
