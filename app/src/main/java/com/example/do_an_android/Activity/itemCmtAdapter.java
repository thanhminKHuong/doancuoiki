package com.example.do_an_android.Activity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.do_an_android.Model.BinhLuanModel;
import com.example.do_an_android.R;

import java.util.List;

public class itemCmtAdapter extends ArrayAdapter<BinhLuanModel> {

    @NonNull
    Activity context ;  int resource ;  @NonNull
    List<BinhLuanModel> objects ;

    public itemCmtAdapter(@NonNull Activity context, int resource, @NonNull List<BinhLuanModel> objects) {
        super(context, resource, objects);
        this.context = context ;
        this.resource = resource ;
        this.objects = objects ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);

        TextView txtName = row.<TextView>findViewById(R.id.name_user_cmt);
        TextView txtContent = row.<TextView>findViewById(R.id.content_cmt);

        txtName.setText(objects.get(position).getTen().toString());
        txtContent.setText(objects.get(position).getNoidung());

        return row ;
    }
}
