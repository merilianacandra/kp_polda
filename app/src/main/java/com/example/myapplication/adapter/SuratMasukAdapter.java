package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.DetailSuratMasukActivityActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.DataSurat;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SuratMasukAdapter extends RecyclerView.Adapter<SuratMasukAdapter.MyViewHolder> {

    List<DataSurat> my_list;
    List<DataSurat> my_listfull;
    Context context;

    public SuratMasukAdapter(Context context, List<DataSurat> my_list) {
        this.my_list = my_list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_surat,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DataSurat model=my_list.get(position);
        holder.mno_surat.setText(model.getNo_surat());
        holder.masal.setText(model.getAsal());
        holder.mtanggal.setText(model.getTgl_surat());
        holder.mperihal.setText(model.getPerihal());
        holder.mCardViewSurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailSuratMasukActivityActivity.class);
                intent.putExtra("id_surat", model.getId_surat());
                intent.putExtra("no_surat", model.getNo_surat());
                intent.putExtra("no_agenda",model.getNo_agenda());
                intent.putExtra("asal",model.getAsal());
                intent.putExtra("perihal",model.getPerihal());
                intent.putExtra("isi",model.getIsi_ringkas());
                intent.putExtra("file",model.getFile_surat());
                intent.putExtra("derajat_surat",model.getDerajat_surat());
                intent.putExtra("tersier",model.getTersier());
                intent.putExtra("jenis_naskah_dinas",model.getJenis_naskah_dinas());
                intent.putExtra("lampiran",model.getLampiran());
                intent.putExtra("tanggal",model.getTgl_surat());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mno_surat, masal, mtanggal, mperihal;
        public CardView mCardViewSurat;

        public MyViewHolder(View itemView) {
            super(itemView);

            mno_surat= itemView.findViewById(R.id.no_surat);
            masal= itemView.findViewById(R.id.asal);
            mtanggal=itemView.findViewById(R.id.tanggal);
            mperihal=itemView.findViewById(R.id.perihal);
            mCardViewSurat=itemView.findViewById(R.id.cvSurat);
        }
    }


//    public Filter getFilter() {
//        return exampleFilter;
//    }
//
//    private Filter exampleFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<ModelJasa> filteredList = new ArrayList<>();
//
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(my_listfull);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//
//                for (ModelJasa item : my_listfull) {
//                    if (item.getAlamat().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
//                    if (item.getNama().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
//                    if (item.getBuka().toLowerCase().contains(filterPattern)) {
//                        filteredList.add(item);
//                    }
//                }
//            }
//
//            FilterResults results = new FilterResults();
//            results.values = filteredList;
//
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            my_list.clear();
//            my_list.addAll((List) results.values);
//            notifyDataSetChanged();
//        }
//    };
}


