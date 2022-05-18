package com.example.do_an_android.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.do_an_android.Admin.Admin;
import com.example.do_an_android.R;
import com.example.do_an_android.Util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ThongTinCaNhan extends AppCompatActivity {
    DatePickerDialog.OnDateSetListener setListener;
    TextView txtemail,txtten,txtsdt,txtgender,txtngaysinh,txtpass,admin_profile,admin_logout,thaydoithongtin,luuthaydoi, huythaydoi;
    LinearLayout layout_admin_profile;
    int type_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Anhxa();
        getData();
        Sukien();
        txtngaysinh.setClickable(false);
    }

    private void Sukien() {
        admin_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), DangNhap.class));
            }
        });
        admin_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), Admin.class));
            }
        });
    }

    private void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getuserbyemail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
//                    Log.d("kiemtraso",jsonObject.names() + " ");
                    String email_profile = jsonObject.getString("email");
                    String namuser_profile = jsonObject.getString("nameuser");
                    String birthday_profile = jsonObject.getString("birthday");
                    String phone_profile = jsonObject.getString("phone");
                    String gender_profile = jsonObject.getString("gender");
                    String pass_profile = jsonObject.getString("password");
                    int type = jsonObject.getInt("typeuser");

                    type_user = type;
                    if (type_user == 1){
                        layout_admin_profile.setVisibility(View.VISIBLE);
                    }

                    txtemail.setText(email_profile + "");
                    txtten.setText(namuser_profile + "");
                    txtsdt.setText(phone_profile + "");
                    txtgender.setText(gender_profile + "");
                    txtpass.setText(pass_profile + "");
                    txtngaysinh.setText(birthday_profile + "");




                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
                param.put("email", TrangChu.email_user);// trung vs php
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void backToHome(View view) {
        finish();
       Intent intent= new Intent(this, TrangChu.class);
//       String email_user = txtemail.getText().toString().trim();
//       intent.putExtra("email_user", email_user);
       startActivity(intent);
    }
    public  void Anhxa(){
        txtemail    = findViewById(R.id.txt_emailprofile);
        txtten      = findViewById(R.id.txt_tenprofile);
        txtsdt      = findViewById(R.id.txt_sdtprofile);
        txtgender   = findViewById(R.id.txt_genderprofile);
        txtngaysinh = findViewById(R.id.txt_ngaysinhprofile);
        txtpass     = findViewById(R.id.txt_passprofile);
        admin_logout = findViewById(R.id.admin_logout);
        admin_profile = findViewById(R.id.admin_profile);
        layout_admin_profile = findViewById(R.id.layout_admin_profile);
        layout_admin_profile.setVisibility(View.GONE);
        thaydoithongtin = findViewById(R.id.thaydoithongtin);
        luuthaydoi = findViewById(R.id.luuthaydoi);
        luuthaydoi.setVisibility(View.GONE);
        huythaydoi = findViewById(R.id.huythaydoi);
        huythaydoi.setVisibility(View.GONE);
        thaydoithongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtten.setEnabled(true);
                txtten.setBackgroundResource(R.drawable.custom_edittext_noframe_no);
                txtsdt.setBackgroundResource(R.drawable.custom_edittext_noframe_no);
                txtgender.setBackgroundResource(R.drawable.custom_edittext_noframe_no);
                txtpass.setBackgroundResource(R.drawable.custom_edittext_noframe_no);
                txtngaysinh.setBackgroundResource(R.drawable.custom_edittext_noframe_no);
                txtsdt.setEnabled(true);

                txtgender.setEnabled(true);
                txtngaysinh.setClickable(true);
                txtpass.setEnabled(true);

                luuthaydoi.setVisibility(View.VISIBLE);
                huythaydoi.setVisibility(View.VISIBLE);
            }
        });
        luuthaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtten.setEnabled(false);
                txtsdt.setEnabled(false);
                txtgender.setEnabled(false);
                txtpass.setEnabled(false);
                txtngaysinh.setClickable(false);
                txtten.setBackgroundResource(R.drawable.custom_edittext_noframe);
                txtsdt.setBackgroundResource(R.drawable.custom_edittext_noframe);
                txtgender.setBackgroundResource(R.drawable.custom_edittext_noframe);
                txtpass.setBackgroundResource(R.drawable.custom_edittext_noframe);
                txtngaysinh.setBackgroundResource(R.drawable.custom_edittext_noframe);

                luuthaydoi.setVisibility(View.GONE);
                huythaydoi.setVisibility(View.GONE);
                updateInfor();
            }
        });
        huythaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                txtten.setEnabled(false);
                txtsdt.setEnabled(false);
                txtgender.setEnabled(false);
                txtpass.setEnabled(false);
                txtten.setBackgroundResource(R.drawable.custom_edittext_noframe);
                txtsdt.setBackgroundResource(R.drawable.custom_edittext_noframe);
                txtgender.setBackgroundResource(R.drawable.custom_edittext_noframe);
                txtpass.setBackgroundResource(R.drawable.custom_edittext_noframe);
                txtngaysinh.setBackgroundResource(R.drawable.custom_edittext_noframe);

                luuthaydoi.setVisibility(View.GONE);
                huythaydoi.setVisibility(View.GONE);
                txtngaysinh.setClickable(false);
            }
        });
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        setListener = (view, year1, month1, day1) -> {
            month1 = month1 + 1;
            String date = day1 + "/" + month1 + "/" + year1;
            txtngaysinh.setText(date);
        };
        txtngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ThongTinCaNhan.this, R.style.DatePickerTheme
                        , setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
    }


    private void updateInfor() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.updateinfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    Toast.makeText(getApplicationContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Lỗi: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("email", TrangChu.email_user.trim());
                param.put("nameuser",txtten.getText().toString().trim());
                param.put("birthday",txtngaysinh.getText().toString().trim());
                param.put("gender",txtgender.getText().toString().trim());
                param.put("phone",txtsdt.getText().toString().trim());
                param.put("password",MD5(txtpass.getText().toString().trim()));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(),TrangChu.class));
    }
    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){
        }
        return null;
    }
}