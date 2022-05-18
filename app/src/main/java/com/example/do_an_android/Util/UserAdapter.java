package com.example.do_an_android.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.do_an_android.Model.User;
import com.example.do_an_android.R;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    Context context;
    ArrayList<User> arrayusers;

    public UserAdapter(Context context, ArrayList<User> arrayusers) {
        this.context = context;
        this.arrayusers = arrayusers;
    }

    @Override
    public int getCount() {
        return arrayusers.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayusers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }




    public class ViewHolder{
        public TextView ten, email, sdt;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_user, null);
            viewHolder.ten = view.findViewById(R.id.txt_tenuser);
            viewHolder.email = view.findViewById(R.id.txt_emailuser);
            viewHolder.sdt = view.findViewById(R.id.txt_sdtuser);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        User us = (User) getItem(i);
        viewHolder.ten.setText(us.getNameuser()+"");
        viewHolder.email.setText(us.getEmail()+"");
        viewHolder.sdt.setText(us.getPhone());
        return view;
    }
}
