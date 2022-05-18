package com.example.do_an_android.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.do_an_android.R;
import com.example.do_an_android.Util.Server;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class DatHang extends AppCompatActivity {

    private static final String FILE_NAME = "myFile";
    SharedPreferences sharedPreferences;
    private static int idorder,tien;
    TextView tongtien,dathang,huybo;
    EditText ten,diachi,sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang);
        anhxa();
        sharedPreferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        idorder = sharedPreferences.getInt("idorder_gh", 0);
        tien = sharedPreferences.getInt("tongtien", 0);

        DecimalFormat formatter = new DecimalFormat("###,###,###");
        tongtien.setText("Tổng tiền: "+formatter.format(tien)+" VND");


        dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ten.getText().toString().equals("")||diachi.getText().toString().equals("")||sdt.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                insertDatHang();
                updateOrder();}
            }
        });
        huybo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(DatHang.this,GioHang.class));
            }
        });

    }

    private void anhxa(){
        dathang=(TextView) findViewById(R.id.dat_hang_dh);
        ten=(EditText) findViewById(R.id.hoten_dh);
        diachi=(EditText)findViewById(R.id.dia_chi_dh);
        sdt=(EditText)findViewById(R.id.so_dien_thoai_dh);
        tongtien=(TextView) findViewById(R.id.tong_tien_thanh_toan_dh);
        huybo=(TextView) findViewById(R.id.huy_dat_hang);
    }
    private void insertDatHang(){
        String ten = this.ten.getText().toString().trim();
        String sdt = this.sdt.getText().toString().trim();
        String dc = this.diachi.getText().toString().trim();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.insertdathang, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                if(response.equals("Done")){
                    Toast.makeText(getApplicationContext(), "Đặt hàng thành công !", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), TrangChu.class));
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("hoten", ten);
                param.put("sdt", sdt);
                param.put("diachi", dc);
                param.put("tongtien",String.valueOf(tien));
                param.put("idorder",String.valueOf(idorder));
                return param;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void updateOrder(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.updateorder, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                if(response.equals("Done")){
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
//                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("totalprice",String.valueOf(tien));
                param.put("idorder",String.valueOf(idorder));
                return param;
            }
        };
        requestQueue.add(stringRequest);

    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(),GioHang.class));
    }
}