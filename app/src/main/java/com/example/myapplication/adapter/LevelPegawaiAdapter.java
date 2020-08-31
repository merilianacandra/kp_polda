package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.DataLevel;
import com.example.myapplication.data.DataLevelPegawai;

import java.util.ArrayList;

public class LevelPegawaiAdapter extends BaseAdapter {

    private Context activity;
    private ArrayList<DataLevelPegawai> data;
    private static LayoutInflater inflater = null;
    private View vi;
    private ViewHolder viewHolder;

    public LevelPegawaiAdapter(Context context, ArrayList<DataLevelPegawai> items) {
        this.activity = context;
        this.data = items;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        vi = view;
        final int pos = position;
        DataLevelPegawai items = data.get(pos);

        if(view == null) {
            vi = inflater.inflate(R.layout.list_level_pegawai, null);
            viewHolder = new ViewHolder();
            viewHolder.checkBox = (CheckBox) vi.findViewById(R.id.cb);
            viewHolder.level = (TextView) vi.findViewById(R.id.nama_level);
            vi.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.level.setText(items.getLevelPegawai());
        }

        if(items.isCheckbox()){
            viewHolder.checkBox.setChecked(true);
        } else {
            viewHolder.checkBox.setChecked(false);
        }

        return vi;
    }

    public ArrayList<DataLevelPegawai> getAllData(){
        return data;
    }

    public void setCheckBox(int position){
        DataLevelPegawai items = data.get(position);
        items.setCheckbox(!items.isCheckbox());
        notifyDataSetChanged();
    }

    public class ViewHolder{
        TextView level;
        CheckBox checkBox;
    }
}