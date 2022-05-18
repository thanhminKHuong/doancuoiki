package com.example.do_an_android.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.do_an_android.Activity.GioHang;
import com.example.do_an_android.Model.cart;
import com.example.do_an_android.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class itemInCartAdapter extends BaseAdapter {
    Context context;
    ArrayList<cart> arraygiohang;


    public itemInCartAdapter(Context context, ArrayList<cart> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public cart getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public  class ViewHolder{
        public TextView title, subtitle, motasanpham,them,giam,soluong,tongtien,xoa;
        //public final TextView soluong;
        public String iddetail,giasp,tong;
        ImageView hinh;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        itemInCartAdapter.ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new itemInCartAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cart_item, null);
            viewHolder.title = view.findViewById(R.id.tenSanPham);
            viewHolder.subtitle = view.findViewById(R.id.giaTienSanPham);
            viewHolder.them = view.findViewById(R.id.tang);
            viewHolder.soluong = view.findViewById(R.id.soLuongSanPham);
            viewHolder.hinh = view.findViewById(R.id.hinhSanPham);
            ViewHolder finalViewHolder = viewHolder;
            viewHolder.tongtien = view.findViewById(R.id.tongTienSanPham);
            viewHolder.them.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tangsosp(finalViewHolder);
                    ((Activity) context).finish();
                    context.startActivity(new Intent(context,GioHang.class));
                }
            });
            viewHolder.giam = view.findViewById(R.id.giam);
            viewHolder.giam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    giamsosp(finalViewHolder);
                    ((Activity) context).finish();
                    context.startActivity(new Intent(context,GioHang.class));
                }
            });
            viewHolder.xoa = view.findViewById(R.id.xoaKhoiGioHang);
            viewHolder.xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    xoasp(finalViewHolder);
                    ((Activity) context).finish();
                    context.startActivity(new Intent(context,GioHang.class));
                }
            });

            //viewHolder.motasanpham = view.findViewById(R.id.mota_sanpham);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (itemInCartAdapter.ViewHolder) view.getTag();
        }
        cart sanpham = (cart)  getItem(i);
        viewHolder.title.setText(sanpham.getNameproduct()+"");
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        viewHolder.subtitle.setText(formatter.format(sanpham.getPrice())+""+" VNĐ");
        viewHolder.iddetail= String.valueOf(sanpham.getIdcart());
        viewHolder.soluong.setText(String.valueOf(sanpham.getSum()));
        viewHolder.giasp=String.valueOf(sanpham.getPrice());
        viewHolder.tongtien.setText(formatter.format(sanpham.getTotalprice())+""+" VNĐ");
        viewHolder.tong=String.valueOf(sanpham.getTotalprice());
        Glide.with(context).load(sanpham.getHinh()).into(viewHolder.hinh);
        //viewHolder.motasanpham.setText(sanpham.getBnt());

        return view;
    }

    private void tangsosp(ViewHolder viewHolder){
        int sl= Integer.parseInt((String) viewHolder.soluong.getText());
        sl+=1;
        String t= ""+sl;
        viewHolder.soluong.setText(t);
        String id=(viewHolder.iddetail);
        updateSoLuong(id,t);
        String tt=String.valueOf( sl* Integer.parseInt(viewHolder.giasp));


        viewHolder.tongtien.setText(tt+""+" VNĐ");
    }
    private void giamsosp(ViewHolder viewHolder){
        int sl= Integer.parseInt((String) viewHolder.soluong.getText());
        if(sl==1){
            return;
        }
        else {
            sl-=1;
            String t= ""+sl;
            viewHolder.soluong.setText(t);
            String id=(viewHolder.iddetail);
            updateSoLuong(id,t);
            String tt=String.valueOf( sl* Integer.parseInt(viewHolder.giasp));

            viewHolder.tongtien.setText(tt+""+" VNĐ");
        }
    }
    private void xoasp(ViewHolder viewHolder){
        String id= viewHolder.iddetail;
        deleteItemInCart(id);
    }

    private void updateSoLuong(String id,String sum){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.updateItemInCart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Done")){
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Lỗi " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("iddetail", id);
                param.put("sum", sum);

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    private  void deleteItemInCart(String id){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.deletedItemInCart, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Done")){
                    Toast.makeText(context, "Xoa thành công", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(context, "Xoa thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Lỗi 2 " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();

                param.put("iddetail", id);

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}