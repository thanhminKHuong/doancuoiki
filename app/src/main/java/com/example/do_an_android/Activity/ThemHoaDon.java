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
import com.example.do_an_android.R;
import com.example.do_an_android.Util.Server;

import java.util.HashMap;
import java.util.Map;

public class ThemHoaDon extends AppCompatActivity {

    private EditText txttrangthai,txtdate,txtdiachi,txtiduser;
    private TextView btnthemHD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themhoadon);
        txttrangthai= (EditText) findViewById(R.id.txt_trangthaihoadon);
        txtdate= (EditText) findViewById(R.id.txt_ngaylaphoadon);
        txtdiachi= (EditText) findViewById(R.id.txt_diachihoadon);
        txtiduser= (EditText) findViewById(R.id.txt_iduser);

        btnthemHD = (TextView) findViewById(R.id.btn_themhoadon);
        btnthemHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemMoi();
            }
        });
    }
    public void ThemMoi( ) {
        String status = this.txttrangthai.getText().toString().trim();
        String date = this.txtdate.getText().toString().trim();
        String address = this.txtdiachi.getText().toString().trim();
        String iduser = this.txtiduser.getText().toString().trim();

//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat simpleformat = new SimpleDateFormat("dd/MM/yyyy");
//        Format f = new SimpleDateFormat("dd/MM/yyyy");
//        String datecreate = f.format(new Date());

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.addHD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Done")){
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), TrangChu.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
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
                param.put("stt", status);
                param.put("dateorder", date);
                param.put("address", address);
                param.put("iduser", iduser);

                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(ThemHoaDon.this,TrangChu.class));
    }
}
