package com.example.do_an_android.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.do_an_android.R;

import java.util.List;

public class MyAdapterSlider extends PagerAdapter {


    List<Integer> list ;

    MyAdapterSlider(List<Integer>  list)
    {
        this.list = list ;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View  view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slide,container,false);
        ImageView imageView = view.<ImageView>findViewById(R.id.img_slider_item);
         imageView.setImageResource(list.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }
}
