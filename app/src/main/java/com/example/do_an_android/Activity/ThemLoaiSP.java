package com.example.do_an_android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.do_an_android.Admin.DanhSachLoaiSP_Admin;
import com.example.do_an_android.R;
import com.example.do_an_android.Util.Server;

import java.util.HashMap;
import java.util.Map;

public class ThemLoaiSP extends AppCompatActivity {
    private EditText txttenloai;
    private TextView btnthemloai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themloaisanpham);
        txttenloai = (EditText) findViewById(R.id.txt_themtenloai);

        btnthemloai = (TextView) findViewById(R.id.btn_themloai);
        btnthemloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemMoi();

            }
        });
    }

    public void ThemMoi() {
        String nametype = this.txttenloai.getText().toString().trim();

        if (nametype.equals("")) {
            Toast.makeText(getApplicationContext(), "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.addloaisp, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    if (response.equals("Done")) {
                        Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), DanhSachLoaiSP_Admin.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Lỗi " + error.toString(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("nametype", nametype);

                    return param;
                }
            };
            requestQueue.add(stringRequest);
        }
    }
}

