package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.DetailDisposisiMasukActivity;
import com.example.myapplication.DetailSuratMasukActivityActivity;
import com.example.myapplication.R;
import com.example.myapplication.data.DataDisposisi;
import com.example.myapplication.data.DataSurat;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DisposisiSuratAdapter extends RecyclerView.Adapter<DisposisiSuratAdapter.MyViewHolder> {

    List<DataDisposisi> my_list;
    List<DataDisposisi> my_listfull;
    Context context;

    public DisposisiSuratAdapter(Context context, List<DataDisposisi> my_list) {
        this.my_list = my_list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_disposisi_surat,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final DataDisposisi model=my_list.get(position);
        holder.mderajat.setText(model.getDerajat_surat());
        holder.masal.setText("Dari : "+model.getNama_asal());
        holder.mkepada.setText("Kepada : "+model.getNama());
        holder.mtanggal.setText(model.getTgl_disposisi());
        holder.mperihal.setText(model.getPerihal());
        int status_baca = Integer.parseInt(model.getStatus_disposisi());
        String status_baca1= null;
        if (status_baca == 1){
            status_baca1 = "Belum Dibaca";
        } if (status_baca == 2) {
            status_baca1 = "Sudah Dibaca";
        }
        holder.mbaca.setText(status_baca1);
        int status_buat = Integer.parseInt(model.getStatus_buat());
        String status_buat1= null;
        if (status_buat == 1){
            status_buat1 = "Belum Didisposisi";
        } if (status_buat == 2) {
            status_buat1 = "Sudah Didisposisi";
        }
        holder.mdisposisi.setText(status_buat1);
        holder.mCardViewDisposisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailDisposisiMasukActivity.class);
                intent.putExtra("id_disposisi", model.getId_disposisi());
                intent.putExtra("id_surat", model.getId_surat());
                intent.putExtra("no_surat", model.getNo_surat());
                intent.putExtra("no_agenda",model.getNo_agenda());
                intent.putExtra("asal",model.getNama_asal());
                intent.putExtra("tujuan",model.getNama());
                intent.putExtra("perihal",model.getPerihal());
                intent.putExtra("isi",model.getIsi_disposisi());
                intent.putExtra("file",model.getFile_disposisi());
                intent.putExtra("derajat_surat",model.getDerajat_surat());
                intent.putExtra("tersier",model.getTersier());
                intent.putExtra("jenis_naskah_dinas",model.getJenis_naskah_dinas());
                intent.putExtra("lampiran",model.getLampiran());
                intent.putExtra("tanggal",model.getTgl_disposisi());
                intent.putExtra("nama_asal",model.getNama_asal());
                intent.putExtra("status_baca",model.getStatus_disposisi());
                intent.putExtra("status_buat",model.getStatus_buat());
                intent.putExtra("id_tujuan",model.getTujuan());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return my_list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mderajat, masal, mtanggal, mperihal, mkepada, mbaca, mdisposisi;
        public CardView mCardViewDisposisi;

        public MyViewHolder(View itemView) {
            super(itemView);

            mderajat = itemView.findViewById(R.id.derajat);
            masal= itemView.findViewById(R.id.asal);
            mtanggal=itemView.findViewById(R.id.tanggal);
            mperihal=itemView.findViewById(R.id.perihal);
            mkepada=itemView.findViewById(R.id.kepada);
            mbaca=itemView.findViewById(R.id.ket_baca);
            mdisposisi=itemView.findViewById(R.id.ket_disposisi);
            mCardViewDisposisi=itemView.findViewById(R.id.cvDisposisi);
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


