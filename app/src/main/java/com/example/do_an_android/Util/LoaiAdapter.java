package com.example.do_an_android.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.do_an_android.Model.DienThoai;
import com.example.do_an_android.Model.LoaiSanPham;
import com.example.do_an_android.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<LoaiSanPham> arrayloaisp;

    public LoaiAdapter(Context context, ArrayList<LoaiSanPham> arrayloaisp) {
        this.context = context;
        this.arrayloaisp = arrayloaisp;
    }

    @Override
    public int getCount() {
        return arrayloaisp.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayloaisp.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }




    public class ViewHolder{
        public TextView tenloai ;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LoaiAdapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new LoaiAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_loaisp, null);
            viewHolder.tenloai = view.findViewById(R.id.txt_loaispAdmin);

            view.setTag(viewHolder);
        }
        else {
            viewHolder = (LoaiAdapter.ViewHolder) view.getTag();
        }
        LoaiSanPham loaisp = (LoaiSanPham) getItem(i);
        viewHolder.tenloai.setText(loaisp.getNametype()+"");


        return view;
    }
}
