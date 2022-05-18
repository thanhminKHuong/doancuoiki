package com.example.do_an_android.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.do_an_android.Model.DienThoai;
import com.example.do_an_android.Model.LoaiSanPham;
import com.example.do_an_android.R;
import com.example.do_an_android.Util.LoaiAdapter;
import com.example.do_an_android.Util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThemSP extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText nameproduct,maloai,price,soluong,manhinh,hedieuhanh,cmrsau,cmrtruoc,chip,ram,bonhotrong,sim,pinsac,hinhanhsp;
    private TextView btnADD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setContentView(R.layout.activity_themdienthoai);
        nameproduct= (EditText) findViewById(R.id.txtTenDT);
        price= (EditText) findViewById(R.id.txt_Giasp_themdt);
        hinhanhsp = this.<EditText>findViewById(R.id.txt_hinhanhsanphamne);
        soluong= (EditText) findViewById(R.id.txt_SoLuongsp_themdt);
        maloai= (EditText) findViewById(R.id.txt_MaLoai_themdt);
        manhinh= (EditText) findViewById(R.id.txt_ManHinh_themdt);
        hedieuhanh= (EditText) findViewById(R.id.txt_hdh_themdt);
        cmrsau= (EditText) findViewById(R.id.txt_CMRSau_themdt);
        cmrtruoc= (EditText) findViewById(R.id.txt_CMRTruoc_themdt);
        chip= (EditText) findViewById(R.id.txt_Chipsp_themdt);
        ram= (EditText) findViewById(R.id.txt_Ramsp_themdt);
        bonhotrong= (EditText) findViewById(R.id.txt_Bntsp_themdt);
        sim= (EditText) findViewById(R.id.txt_Simsp_themdt);
        pinsac= (EditText) findViewById(R.id.txt_PinSacsp_themdt);
        btnADD = (TextView) findViewById(R.id.btnThemDT);
        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemMoi();
            }
        });



    }
    public void ThemMoi( ) {

        String nameproduct = this.nameproduct.getText().toString().trim();
        String price = this.price.getText().toString().trim();
        String soluong = this.soluong.getText().toString().trim();
        String maloai = this.maloai.getText().toString().trim();
        String manhinh = this.manhinh.getText().toString().trim();
        String hedieuhanh = this.hedieuhanh.getText().toString().trim();
        String cmrsau = this.cmrsau.getText().toString().trim();
        String cmrtruoc = this.cmrtruoc.getText().toString().trim();
        String chip = this.chip.getText().toString().trim();
        String ram = this.ram.getText().toString().trim();
        String bonhotrong = this.bonhotrong.getText().toString().trim();
        String sim = this.sim.getText().toString().trim();
        String pinsac = this.pinsac.getText().toString().trim();
        String hinhanhsanpham = this.hinhanhsp.getText().toString().trim();

        if (nameproduct.equals("") || price.equals("")|| maloai.equals("") || manhinh.equals("") || hedieuhanh.equals("")||cmrsau.equals("")||
                    cmrtruoc.equals("")||chip.equals("")||ram.equals("")||bonhotrong.equals("")||sim.equals("")||pinsac.equals(""))
        {
                Toast.makeText(getApplicationContext(),"Vui lòng điền đủ thông tin",Toast.LENGTH_SHORT).show();
                return;
        } else {

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.addproduct, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(), TrangChu.class));

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
                    param.put("nameproduct", nameproduct);
                    param.put("price", price);
                    param.put("manhinh", manhinh);
                    param.put("hdh", hedieuhanh);
                    param.put("camerasau", cmrsau);
                    param.put("cameratruoc", cmrtruoc);
                    param.put("chip", chip);
                    param.put("ram", ram);
                    param.put("bnt", bonhotrong);
                    param.put("sim", sim);
                    param.put("pinsac", pinsac);
                    param.put("sum", soluong);
                    param.put("idtype", maloai);
                    param.put("hinhanh",hinhanhsanpham);
                    return param;
                }
            };
            requestQueue.add(stringRequest);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}