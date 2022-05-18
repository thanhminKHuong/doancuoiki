package com.example.do_an_android.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.bumptech.glide.Glide;
import com.example.do_an_android.Model.DienThoai;
import com.example.do_an_android.R;

import java.util.List;

public class CategoryAdapter extends Adapter<CategoryAdapter.CategoryViewHolder> {


    private Context mContext ;
    private List<DienThoai> categoryList ;

    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView ;
        private TextView textView;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.<ImageView>findViewById(R.id.hinhanhsanpham);
            textView = itemView.<TextView>findViewById(R.id.tensanphamne);
        }
    }
    public void setData(List<DienThoai> data)
    {
        this.categoryList = data ;
        notifyDataSetChanged();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(categoryList != null)
        {
            return categoryList.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DienThoai category = categoryList.get(position);
        if(category == null)
        {
            return ;
        }else{
            holder.textView.setText(category.getNameproduct().toString());
            Glide.with(mContext).load(category.getHinh()).into(holder.imageView);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(mContext,ChiTietSanPham.class);

                                   Intent intent = new Intent(mContext,ChiTietSanPham.class);
               int idpro = categoryList.get(position).getIdproduct();
               ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
//               chiTietSanPham.getBinhLuanByIdProduct();
               chiTietSanPham.hienthiclick();
               intent.putExtra("idproduct", categoryList.get(position).getIdproduct());
               mContext.startActivity(intent);
//                    mContext.startActivity(intent);
                }
            });
        }
    }
}
